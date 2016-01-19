/*
 * 版权所有 (c) 2015 。 李倍存 （iPso）。
 * 所有者对该文件所包含的代码的正确性、执行效率等任何方面不作任何保证。
 * 所有个人和组织均可不受约束地将该文件所包含的代码用于非商业用途。若需要将其用于商业软件的开发，请首先联系所有者以取得许可。
 */

package org.ipso.lbc.common.exception;

/**
 * 李倍存 创建于 2015-03-03 15:57。电邮 1174751315@qq.com。
 */
public class AppCheckedException extends Exception {
    public  String getExceptionType(){
        return "未知受检异常";
    }
    public enum eScope {USER, DEVELOPER, UNDEFINED}

    public eScope getScope() {
        return scope;
    }

    private eScope scope = eScope.UNDEFINED;
    private String message;

    public AppCheckedException(String message, eScope scope, Object context) {
        super();
        String prefix;
        switch (scope) {
            case USER:
                prefix = "用户可见的异常提示：";
                break;
            case UNDEFINED:
                prefix = "未指定可见性的异常提示：";
                break;
            case DEVELOPER:
            default:
                prefix = "开发者可见的异常提示：";
                break;

        }
        this.message = prefix + message;
        this.scope = scope;
        this.context = context;
    }

    private Object context;

    public AppCheckedException() {
        this(MSG.USER__DEFAULT, eScope.UNDEFINED, null);
    }

    public AppCheckedException(Object context) {
        this(MSG.USER__DEFAULT, eScope.UNDEFINED, context);
    }

    public AppCheckedException(String message) {
        this(message, eScope.UNDEFINED, null);
    }

    public AppCheckedException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public AppCheckedException(Throwable throwable) {
        super(throwable);
    }


    public AppCheckedException(String message, eScope scope) {
        this(message, scope, null);
    }

    public AppCheckedException(String message, Object context) {
        this(message, eScope.UNDEFINED, context);
    }

    public Object getContext() {
        return this.context;
    }


    public void printStackTrace() {
        System.err.println(message);
        super.printStackTrace();
    }

    public String getMessage() {
        return this.message;
    }


    public class MSG {
        private static final String BEGIN = "[";
        private static final String END = "]";
        private static final String USER__ROOT = BEGIN + "用户可见的异常提示：";
        public static final String USER__CONTRACT_DEVELOPER = USER__ROOT + "请联系开发者（1174751315@qq.com）。" + END;
        public static final String USER__CHECK_INPUT_PARAM = USER__ROOT + "请检查输入。" + END;
        public static final String USER__DEFAULT = USER__ROOT + "无提示信息。" + END;
        private static final String DEV__ROOT = BEGIN + "开发者可见的异常提示：";
        public static final String DEV__CHECK_DATA_LOST = DEV__ROOT + "数据缺失。" + END;
        public static final String DEV__CHECK_DATABASE_CONNECTION = DEV__ROOT + "请检查数据库连接是否正常。" + END;
        public static final String DEV__DATA_ACCESS = DEV__ROOT + "数据访问异常。" + END;
    }
}
