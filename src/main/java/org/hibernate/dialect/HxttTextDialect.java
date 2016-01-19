/*
 * 版权所有 (c) 2015 。 李倍存 （iPso）。
 * 所有者对该文件所包含的代码的正确性、执行效率等任何方面不作任何保证。
 * 所有个人和组织均可不受约束地将该文件所包含的代码用于非商业用途。若需要将其用于商业软件的开发，请首先联系所有者以取得许可。
 */

package org.hibernate.dialect;

import java.sql.Types;

/**
 * An SQL dialect for HXTT Text (CSV).
 */
public class HxttTextDialect extends HxttDialect {

    public HxttTextDialect() {
        super();
        //complete map
        registerColumnType(Types.BIT, "boolean");
        registerColumnType(Types.CHAR, "varchar($l)");
        registerColumnType(Types.VARCHAR, "varchar($l)");
        //registerColumnType(Types.VARCHAR, 254, "varchar($l)");
        registerColumnType(Types.LONGVARCHAR, "longvarchar");
        registerColumnType(Types.NUMERIC, "numeric($p,$s)");
        registerColumnType(Types.DECIMAL, "decimal($p,$s)");
        registerColumnType(Types.BIGINT, "bigint");
        registerColumnType(Types.SMALLINT, "numeric(5,0)"); // HXTT Text (CSV) DON'T SUPPORT SMALLINT
        registerColumnType(Types.TINYINT, "numeric(3,0)");   // HXTT Text (CSV) DON'T SUPPORT TINYINT
        registerColumnType(Types.BOOLEAN, "boolean");
        registerColumnType(Types.INTEGER, "integer");

        registerColumnType(Types.FLOAT, "float");
        registerColumnType(Types.DOUBLE, "double");
        registerColumnType(Types.BINARY, "binary");
        registerColumnType(Types.VARBINARY, "varbinary");
        registerColumnType(Types.LONGVARBINARY, "longvarbinary");

        registerColumnType(Types.DATE, "date");
        registerColumnType(Types.TIME, "time");
        registerColumnType(Types.TIMESTAMP, "timestamp");
        registerColumnType(Types.BLOB, "blob");
        registerColumnType(Types.CLOB, "clob");
        registerColumnType(Types.JAVA_OBJECT, "java_object");
    }

    /**
     * Does this dialect support the <tt>ALTER TABLE</tt> syntax?
     *
     * @return True if we support altering of tables; false otherwise.
     */
    public boolean hasAlterTable() {
        return false;//true;
    }


    /**
     * Does this dialect support identity column key generation?
     *
     * @return True if IDENTITY columns are supported; false otherwise.
     */
    public boolean supportsIdentityColumns() {
        return false;
    }

    /**
     * Whether this dialect have an Identity clause added to the data type or a
     * completely seperate identity data type
     *
     * @return boolean
     */
    public boolean hasDataTypeInIdentityColumn() {
        return false;
    }
}
