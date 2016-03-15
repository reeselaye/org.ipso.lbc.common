package org.ipso.lbc.common.frameworks.logging;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.config.plugins.*;
import org.apache.logging.log4j.core.layout.AbstractStringLayout;
import org.apache.logging.log4j.core.util.Constants;
import org.apache.logging.log4j.core.util.Transform;

import java.io.*;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Outputs events as rows in an HTML table on an HTML page.
 * <p>
 * Appenders using this layout should have their encoding set to UTF-8 or UTF-16, otherwise events containing non ASCII
 * characters could result in corrupted log files.
 * </p>
 */
@Plugin(name = "SimplifiedHtmlLayout", category = "Core", elementType = "Layout", printObject = true)
public class SimplifiedHtmlLayout extends AbstractStringLayout {
    private static final int BUF_SIZE = 256;
    private static final String TRACE_PREFIX = "<br />&nbsp;&nbsp;&nbsp;&nbsp;";
    private static final String REGEXP = Constants.LINE_SEPARATOR.equals("\n") ? "\n" : Constants.LINE_SEPARATOR + "|\n";
    private static final String DEFAULT_CONTENT_TYPE = "text/html";
    public static final String DEFAULT_FONT_FAMILY = "arial,sans-serif";

    private static final String ICONS_DIR = "icon/";
    private static final String EMPTY_TD = "";//"<td align=\"center\">N/A</td>";
    private static final String EMPTY_TH = "";//"<th align=\"center\">N/A</th>";

    private final boolean doNotShowLevel;
    private final boolean doNotShowSource;
    private final boolean doNotShowThread;
    private final boolean doNotShowWholeLoggerName;

    private final boolean useLevelImage;

    private final int maxMessageLength;

    private final String title;

    private final String contentType;

    /**Possible font sizes */
    public static enum FontSize {
        SMALLER("smaller"), XXSMALL("xx-small"), XSMALL("x-small"), SMALL("small"), MEDIUM("medium"), LARGE("large"),
        XLARGE("x-large"), XXLARGE("xx-large"),  LARGER("larger");

        private final String size;

        private FontSize(final String size) {
            this.size = size;
        }

        public String getFontSize() {
            return size;
        }

        public static FontSize getFontSize(final String size) {
            for (final FontSize fontSize : values()) {
                if (fontSize.size.equals(size)) {
                    return fontSize;
                }
            }
            return SMALLER;
        }

        public FontSize larger() {
            return this.ordinal() < XXLARGE.ordinal() ? FontSize.values()[this.ordinal() + 1] : this;
        }
    }

    private final String font;
    private final String fontSize;
    private final String headerSize;

    private SimplifiedHtmlLayout(final boolean doNotShowLevel, boolean doNotShowSource, boolean doNotShowThread, boolean doNotShowWholeLoggerName, boolean useLevelImage, int maxMessageLength, final String title, final String contentType, final Charset charset,
                                 final String font, final String fontSize, final String headerSize) {
        super(charset);
        this.doNotShowLevel = doNotShowLevel;
        this.doNotShowSource = doNotShowSource;
        this.doNotShowThread = doNotShowThread;
        this.doNotShowWholeLoggerName = doNotShowWholeLoggerName;
        this.useLevelImage = useLevelImage;
        this.maxMessageLength = maxMessageLength;
        this.title = title;
        this.contentType = addCharsetToContentType(contentType);
        this.font = font;
        this.fontSize = fontSize;
        this.headerSize = headerSize;
    }

    private String addCharsetToContentType(final String contentType) {
        if (contentType == null) {
            return DEFAULT_CONTENT_TYPE + "; charset=" + getCharset();
        }
        return contentType.contains("charset") ? contentType : contentType + "; charset=" + getCharset();
    }

    /**
     * Format as a String.
     *
     * @param event The Logging Event.
     * @return A String containing the LogEvent as HTML.
     */
    @Override
    public String toSerializable(final LogEvent event) {
        final StringBuilder sbuf = new StringBuilder(BUF_SIZE);

        sbuf.append(Constants.LINE_SEPARATOR).append("<tr>").append(Constants.LINE_SEPARATOR);

        //级别
        sbuf.append(doNotShowLevel ?EMPTY_TD:getTableCellOfLevel(event)).append(Constants.LINE_SEPARATOR);
        //时间
        sbuf.append("<td style=\"width:3.2cm\">");
        sbuf.append(Transform.escapeHtmlTags(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())));
        sbuf.append("</td>").append(Constants.LINE_SEPARATOR);
        //来源
        sbuf.append(doNotShowSource?EMPTY_TD:getTableCellOfSource(event)).append(Constants.LINE_SEPARATOR);
        //信息
        sbuf.append("<td>");
        String msg = event.getMessage().getFormattedMessage();
        Integer len = msg.length();
        if (maxMessageLength > 0 && len>maxMessageLength){
                msg = msg.substring(0, maxMessageLength) + " ... ";
        }
        sbuf.append(Transform.escapeHtmlTags(msg).replaceAll(REGEXP, "<br />"));
        sbuf.append("</td>").append(Constants.LINE_SEPARATOR);
        //线程
        sbuf.append(doNotShowThread?EMPTY_TD:getTableCellOfThread(event)).append(Constants.LINE_SEPARATOR);

        sbuf.append("</tr>").append(Constants.LINE_SEPARATOR);

        if (event.getContextStack() != null && !event.getContextStack().isEmpty()) {
            sbuf.append("<tr><td bgcolor=\"#EEEEEE\" style=\"font-size : ").append(fontSize);
            sbuf.append(";\" colspan=\"6\" ");
            sbuf.append("title=\"Nested Diagnostic Context\">");
            sbuf.append("NDC: ").append(Transform.escapeHtmlTags(event.getContextStack().toString()));
            sbuf.append("</td></tr>").append(Constants.LINE_SEPARATOR);
        }

        if (event.getContextMap() != null && !event.getContextMap().isEmpty()) {
            sbuf.append("<tr><td bgcolor=\"#EEEEEE\" style=\"font-size : ").append(fontSize);
            sbuf.append(";\" colspan=\"6\" ");
            sbuf.append("title=\"Mapped Diagnostic Context\">");
            sbuf.append("MDC: ").append(Transform.escapeHtmlTags(event.getContextMap().toString()));
            sbuf.append("</td></tr>").append(Constants.LINE_SEPARATOR);
        }

        final Throwable throwable = event.getThrown();
        if (throwable != null) {
            sbuf.append("<tr><td bgcolor=\"#993300\" style=\"color:White; font-size : ").append(fontSize);
            sbuf.append(";\" colspan=\"6\">");
            appendThrowableAsHtml(throwable, sbuf);
            sbuf.append("</td></tr>").append(Constants.LINE_SEPARATOR);
        }

        return sbuf.toString();
    }

    protected String getTableCellOfThread(LogEvent event){
        String escapedThread = Transform.escapeHtmlTags(event.getThreadName());
        return "<td>" + escapedThread + "</td>";
    }
    protected String getTableCellOfSource(final  LogEvent event){
        String src = "<td>";
        String logger = Transform.escapeHtmlTags(event.getLoggerName());
        if (logger.matches("(.*)user(.*)")){
            src += "<img src=\"" + ICONS_DIR+"user.png" + "\"/>";
        } else if (logger.matches("(.*)vital(.*)")){
            src += "<img src=\"" + ICONS_DIR+"vital.png" + "\"/>";
        } else if (logger.matches("(.*)important(.*)")){
            src += "<img src=\"" + ICONS_DIR+"important.png" + "\"/>";
        }else {
            if (logger.isEmpty()) {
                src += "[UNKNOWN]";
            }
            else {
                if (doNotShowWholeLoggerName) {
                    try {
                        Integer index;
                        //logger -> org.ipso.lbc.common.util.StringUtils
                        //first -> org.
                        index = logger.indexOf(".")+1;
                        String first = logger.substring(0, index);
                        logger = logger.substring(index);
                        //second -> ipso.lbc.common.util.StringUtils
                        index = logger.indexOf(".")+1;
                        String second = logger.substring(0,index);
                        logger = logger.substring(index);
                        String fina = logger.substring(logger.lastIndexOf(".")+1);
                        src += first + second + " ... "+ fina;
                    }catch (Exception e){
                        src += "[ERROR:CANNOT GET THE WHOLE PACKAGE NAME]";
                    }
                } else {
                    src += logger;
                }
            }
        }
        src += "</td>";
        return src;
    }
    protected String getTableCellOfLevel(final LogEvent event){
        String s = "<td style=\"width:0.6cm\" align = \"center\">";


        if (useLevelImage){//若采用图片
            s += "<img src=\"" + ICONS_DIR ;
            if (event.getLevel().equals(Level.TRACE)){
                s += "trace";
            } else if(event.getLevel().equals(Level.DEBUG)){
                s += "debug";
            } else if(event.getLevel().equals(Level.INFO)){
                s += "info";
                String logger = event.getLoggerName();
                if (logger.matches("(.*)vital(.*)")){
                    s += "-vital";
                } else if (logger.matches("(.*)important(.*)")){
                    s += "-important";
                }
            } else if(event.getLevel().equals(Level.WARN)){
                s += "warn";
            } else if(event.getLevel().equals(Level.ERROR)){
                s += "error";
            } else if(event.getLevel().equals(Level.FATAL)){
                s += "fatal";
            }
            s += ".png\" />";
        }else{
            if (event.getLevel().equals(Level.DEBUG)) {
                s += ("<font color=\"#339933\">");
                s += (Transform.escapeHtmlTags(String.valueOf(event.getLevel())));
                s += ("</font>");
            } else if (event.getLevel().isMoreSpecificThan(Level.WARN)) {
                s += ("<font color=\"#993300\"><strong>");
                s += (Transform.escapeHtmlTags(String.valueOf(event.getLevel())));
                s += ("</strong></font>");
            } else {
                s += (Transform.escapeHtmlTags(String.valueOf(event.getLevel())));
            }
        }


        s += ("</td>");
        return s;
    }

    @Override
    /**
     * @return The content type.
     */
    public String getContentType() {
        return contentType;
    }

    private void appendThrowableAsHtml(final Throwable throwable, final StringBuilder sbuf) {
        final StringWriter sw = new StringWriter();
        final PrintWriter pw = new PrintWriter(sw);
        try {
            throwable.printStackTrace(pw);
        } catch (final RuntimeException ex) {
            // Ignore the exception.
        }
        pw.flush();
        final LineNumberReader reader = new LineNumberReader(new StringReader(sw.toString()));
        final ArrayList<String> lines = new ArrayList<String>();
        try {
            String line = reader.readLine();
            while (line != null) {
                lines.add(line);
                line = reader.readLine();
            }
        } catch (final IOException ex) {
            if (ex instanceof InterruptedIOException) {
                Thread.currentThread().interrupt();
            }
            lines.add(ex.toString());
        }
        boolean first = true;
        for (final String line : lines) {
            if (!first) {
                sbuf.append(TRACE_PREFIX);
            } else {
                first = false;
            }
            sbuf.append(Transform.escapeHtmlTags(line));
            sbuf.append(Constants.LINE_SEPARATOR);
        }
    }

    /**
     * Returns appropriate HTML headers.
     * @return The header as a byte array.
     */
    @Override
    public byte[] getHeader() {
        final StringBuilder sbuf = new StringBuilder();
        sbuf.append("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" ");
        sbuf.append("\"http://www.w3.org/TR/html4/loose.dtd\">");
        sbuf.append(Constants.LINE_SEPARATOR);
        sbuf.append("<html>").append(Constants.LINE_SEPARATOR);
        sbuf.append("<head>").append(Constants.LINE_SEPARATOR);
        sbuf.append("<meta charset=\"").append(getCharset()).append("\"/>").append(Constants.LINE_SEPARATOR);
        sbuf.append("<title>").append(title).append("</title>").append(Constants.LINE_SEPARATOR);
        sbuf.append("<style type=\"text/css\">").append(Constants.LINE_SEPARATOR);
        sbuf.append("<!--").append(Constants.LINE_SEPARATOR);
        sbuf.append("body, table {font-family:").append(font).append("; font-size: ");
        sbuf.append(headerSize).append(";}").append(Constants.LINE_SEPARATOR);
        sbuf.append("th {background: #336699; color: #FFFFFF; text-align: left;}").append(Constants.LINE_SEPARATOR);
        sbuf.append("-->").append(Constants.LINE_SEPARATOR);
        sbuf.append("</style>").append(Constants.LINE_SEPARATOR);
        sbuf.append("</head>").append(Constants.LINE_SEPARATOR);
        sbuf.append("<body bgcolor=\"#FFFFFF\" topmargin=\"6\" leftmargin=\"6\">").append(Constants.LINE_SEPARATOR);
        sbuf.append("<hr size=\"1\" noshade=\"noshade\">").append(Constants.LINE_SEPARATOR);
        sbuf.append("<h1 style=\"color:#0000FF\">Log session start at " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date()) + "</h1>").append(Constants.LINE_SEPARATOR);
        sbuf.append("<br>").append(Constants.LINE_SEPARATOR);
        sbuf.append(
                "<table cellspacing=\"0\" cellpadding=\"4\" border=\"1\" bordercolor=\"#224466\" width=\"100%\">");
        sbuf.append(Constants.LINE_SEPARATOR);

        sbuf.append("<tr>").append(Constants.LINE_SEPARATOR);
        sbuf.append(doNotShowLevel ?EMPTY_TH:"<th>Level</th>").append(Constants.LINE_SEPARATOR);
        sbuf.append("<th>Time</th>").append(Constants.LINE_SEPARATOR);
        sbuf.append(doNotShowSource?EMPTY_TH:"<th>Source</th>").append(Constants.LINE_SEPARATOR);
        sbuf.append("<th>Message</th>").append(Constants.LINE_SEPARATOR);
        sbuf.append(doNotShowThread?EMPTY_TH:"<th>Thread</th>").append(Constants.LINE_SEPARATOR);
        sbuf.append("</tr>").append(Constants.LINE_SEPARATOR);


        return sbuf.toString().getBytes(getCharset());
    }



    /**
     * Returns the appropriate HTML footers.
     * @return the footer as a byet array.
     */
    @Override
    public byte[] getFooter() {
        final StringBuilder sbuf = new StringBuilder();
        sbuf.append("</table>").append(Constants.LINE_SEPARATOR);
        sbuf.append("<br>").append(Constants.LINE_SEPARATOR);
        sbuf.append("</body></html>");
        return getBytes(sbuf.toString());
    }


    @PluginFactory
    public static SimplifiedHtmlLayout createLayout(
            @PluginAttribute(value = "doNotShowLevel", defaultBoolean = false) final boolean doNotShowLevel,
            @PluginAttribute(value = "doNotShowSource", defaultBoolean = false) final boolean doNotShowSource,
            @PluginAttribute(value = "doNotShowThread", defaultBoolean = false) final boolean doNotShowThread,
            @PluginAttribute(value = "doNotShowWholeLoggerName", defaultBoolean = false) final boolean doNotShowWholeLoggerName,
            @PluginAttribute(value = "useLevelImage", defaultBoolean = false) final boolean useLevelImage,
            @PluginAttribute(value = "maxMessageLength", defaultInt = 0) final int maxMessageLength,
            @PluginAttribute(value = "title", defaultString = "Log messages powered by Log4j2") final String title,
            @PluginAttribute("contentType") String contentType,
            @PluginAttribute(value = "charset", defaultString = "UTF-8") final Charset charset,
            @PluginAttribute("fontSize") String fontSize,
            @PluginAttribute(value = "fontName", defaultString = DEFAULT_FONT_FAMILY) final String font) {
        final FontSize fs = FontSize.getFontSize(fontSize);
        fontSize = fs.getFontSize();
        final String headerSize = fs.larger().getFontSize();
        if (contentType == null) {
            contentType = DEFAULT_CONTENT_TYPE + "; charset=" + charset;
        }
        return new SimplifiedHtmlLayout(doNotShowLevel, doNotShowSource, doNotShowThread, doNotShowWholeLoggerName, useLevelImage, maxMessageLength, title, contentType, charset, font, fontSize, headerSize);
    }

    /**
     * Creates an HTML Layout using the default settings.
     *
     * @return an HTML Layout.
     */
    public static SimplifiedHtmlLayout createDefaultLayout() {
        return newBuilder().build();
    }

    @PluginBuilderFactory
    public static Builder newBuilder() {
        return new Builder();
    }

    public static class Builder implements org.apache.logging.log4j.core.util.Builder<SimplifiedHtmlLayout> {

        @PluginBuilderAttribute
        private boolean doNotShowSource = false;
        @PluginBuilderAttribute
        private boolean doNotShowThread = false;

        @PluginBuilderAttribute
        private boolean doNotShowWholeLoggerName = false;
        @PluginBuilderAttribute
        private boolean useLevelImage = false;
        @PluginBuilderAttribute
        private int maxMessageLength = 0;
        @PluginBuilderAttribute
        private boolean doNotShowLevel = false;

        @PluginBuilderAttribute
        private String title = "";

        @PluginBuilderAttribute
        private String contentType = null; // defer default value in order to use specified charset

        @PluginBuilderAttribute
        private Charset charset = Constants.UTF_8;

        @PluginBuilderAttribute
        private FontSize fontSize = FontSize.SMALLER;

        @PluginBuilderAttribute
        private String fontName = DEFAULT_FONT_FAMILY;

        private Builder() {
        }

        public Builder withLocationInfo(final boolean locationInfo) {
            this.doNotShowLevel = locationInfo;
            return this;
        }

        public Builder withTitle(final String title) {
            this.title = title;
            return this;
        }

        public Builder withContentType(final String contentType) {
            this.contentType = contentType;
            return this;
        }

        public Builder withCharset(final Charset charset) {
            this.charset = charset;
            return this;
        }

        public Builder withFontSize(final FontSize fontSize) {
            this.fontSize = fontSize;
            return this;
        }

        public Builder withFontName(final String fontName) {
            this.fontName = fontName;
            return this;
        }

        @Override
        public SimplifiedHtmlLayout build() {
            // TODO: extract charset from content-type
            if (contentType == null) {
                contentType = DEFAULT_CONTENT_TYPE + "; charset=" + charset;
            }
            return new SimplifiedHtmlLayout(doNotShowLevel, doNotShowSource, doNotShowThread, doNotShowWholeLoggerName, useLevelImage, maxMessageLength, title, contentType, charset, fontName, fontSize.getFontSize(),
                    fontSize.larger().getFontSize());
        }
    }
}
