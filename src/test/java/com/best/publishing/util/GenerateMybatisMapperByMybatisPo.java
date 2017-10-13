package com.best.publishing.util;

import org.junit.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * 功能：通过已写好的mybatis po，生成mybatis需要的mapper xml配置文件.
 * <p>
 * 用法：输入mybatisPoClassPath：mybatis PO 的classpath
 * <p>
 * ****输入tableName： po对应的表名
 * <p>
 * ****输入sequenceName： po对应表的sequence
 *
 * @author bl00035
 */
public class GenerateMybatisMapperByMybatisPo {
    // hibernate po的classpath
    /*
     * com.best.oasis.genidc.biz.utils.model
     *
     * MbUtilsAsyncJobRedo MbUtilsAsyncJobTable
     */

    private static String mybatisPoClassPath = "com.best.publishing.po.common.ProjectPo";
    private static String tableName = "GV_SECRET_INFORMATION";
    private static String sequenceName = "GV_SECRET_INFORMATION_SEQ";
    private static List<String> excludeFidldList = new ArrayList<String>();

    @Test
    public void main() throws ClassNotFoundException {
        Class<?> cls = Class.forName(mybatisPoClassPath);
        excludeFidldList.add("SERIALVERSIONUID");
        printLine("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>");
        printLine("<!DOCTYPE mapper PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\" \"http://mybatis.org/dtd/mybatis-3-mapper.dtd\" >");
        printWhiteLine();
        // namespace
        printLine("<mapper namespace=\"" + mybatisPoClassPath + "\">");
        // tableSection
        printLine("<sql id=\"tableSection\">" + tableName + "</sql>");
        printWhiteLine();
        // sequenceSection
        printLine("<sql id=\"sequenceSection\">" + sequenceName + "</sql>");
        printWhiteLine();
        // poTypeSection
        printLine("<sql id=\"poTypeSection\">" + mybatisPoClassPath + "</sql>");
        printWhiteLine();
        // getSequence
        printLine("<sql id=\"getSequence\">select <include refid=\"sequenceSection\"/>.nextval from dual</sql>");
        printWhiteLine();

        printWhiteLine();
        insertSql(cls); // insert segment

        printWhiteLine();
        updateSql(cls);// update segment

        printWhiteLine();
        resultMap(mybatisPoClassPath, cls);

        printWhiteLine();
        generalSearchWhereClause();

        printWhiteLine();
        searchPo();

        printWhiteLine();
        searchPoCount();

        printWhiteLine();
        deleteSql(cls);// delete segment

        printWhiteLine();
        getPoByIdSql(cls);

        printWhiteLine();
        getPoByIdListSql(cls);

        printWhiteLine();
        printWhiteLine();
        printWhiteLine();
        printWhiteLine();
        printLine("</mapper>");
    }

    private static void insertSql(Class<?> cls) {
        printLine("<!-- Create Po -->");
        printLine("<insert id=\"createPo\">");
        printLine("  <selectKey keyProperty=\"id\" resultType=\"long\" order=\"BEFORE\">");
        printLine("     <include refid=\"getSequence\"/>");
        printLine("  </selectKey>");
        printLine("  insert into <include refid=\"tableSection\"/> (");
        printLine("      <include refid=\"com.best.publishing.baseMapper.baseFieldCreate\"/>");
        Field[] fieldlist = cls.getDeclaredFields();
        int baseFieldCount = 18;
        // 列出mapper的字段名
        // <!-- 20-->CODE,
        int j = 0;
        String lastValidField = lastValidField(fieldlist);
        for (int i = 0; i < fieldlist.length; i++) {
            Field fld = fieldlist[i];
            if (isContinueField(fld)) {
                continue;
            }

            String fieldName = getSqlFieldName(fld.getName());
            int index = baseFieldCount + j;
            j++;
            String fieldIndex = "      <!--" + index + "-->";
            if (!fieldName.equals(lastValidField)) {
                fieldName = fieldName + ",";
            }


            printLine("      " + fieldIndex + fieldName);
        }
        printLine("      )");
        printLine("      values (");
        printLine("           <include refid=\"com.best.publishing.baseMapper.baseFieldCreateValue\"/>");

        // 列出mybatis po的字段名和字段类型
        // <!-- 36-->#{roleId,jdbcType=DECIMAL},
        j = 0;
        for (int i = 0; i < fieldlist.length; i++) {
            Field fld = fieldlist[i];
            if (isContinueField(fld)) {
                continue;
            }

            String fieldName = fld.getName();
            int index = baseFieldCount + j;
            j++;
            String fieldIndex = "           <!--" + index + "-->";
            String oracleFieldType = getOracleFieldType(fld);
            if (!fieldName.equals(lastValidField)) {
                oracleFieldType = oracleFieldType + "},";
            } else {
                oracleFieldType = oracleFieldType + "}";
            }
            printLine(fieldIndex + "#{" + fieldName + ",jdbcType=" + oracleFieldType);
        }
        printLine("      )");
        printLine("</insert>");
    }

    private static String getSqlFieldName(String fieldName) {
        String _fieldName = fieldName;
        fieldName = "";
        for (int k = 0; k < _fieldName.length(); k++) {

            char c = _fieldName.charAt(k);
            if (c >= 'A' && c <= 'Z') {
                c += 32;
                fieldName += "_" + c;
            } else {
                fieldName += c;
            }
        }
        return fieldName;
    }

    private static void updateSql(Class<?> cls) {
        printLine("<!-- update Po -->");
        printLine("<update id=\"updatePo\">");
        printLine("  update <include refid=\"tableSection\"/>");
        printLine("  set");
        printLine("  <include refid=\"com.best.publishing.baseMapper.abstractPoColumn4Update\"/>");
        Field[] fieldlist = cls.getDeclaredFields();
        int baseFieldCount = 18;
        // <!-- 23-->MENUCODE = #{menuCode,jdbcType=VARCHAR},
        int j = 0;
        String lastValidField = lastValidField(fieldlist);
        for (int i = 0; i < fieldlist.length; i++) {
            Field fld = fieldlist[i];
            if (isContinueField(fld)) {
                continue;
            }

            String fieldName = getSqlFieldName(fld.getName());
            int index = baseFieldCount + j;
            j++;
            String fieldIndex = "      <!--" + index + "-->";

            String oracleFieldName = "";
            String mybatisPoFieldName = fieldName;

            oracleFieldName = getSqlFieldName(fieldName);

            String oracleFieldType = getOracleFieldType(fld);
            if (!fieldName.equals(lastValidField)) {
                oracleFieldType = oracleFieldType + "},";
            } else {
                oracleFieldType = oracleFieldType + "}";
            }
            printLine(fieldIndex + oracleFieldName + " = #{" + mybatisPoFieldName + ",jdbcType=" + oracleFieldType);
        }
        printLine("  where ID = #{id,jdbcType=DECIMAL} and LOCKVERSION = #{lockVersion,jdbcType=DECIMAL}");
        printLine("</update>");
    }

    private static void deleteSql(Class<?> cls) {
        printLine("<!-- delete Po -->");
        printLine("<delete id=\"deletePo\" parameterType=\"long\">");
        printLine("    <include refid=\"com.best.publishing.baseMapper.baseDelete\"/>");
        printLine("</delete>");
    }

    private static void getPoByIdSql(Class<?> cls) {
        printLine("<!-- get Po by id-->");
        printLine("<select id=\"getPoById\" resultMap=\"resultMap\" parameterType=\"long\">");
        printLine("    <include refid=\"com.best.publishing.baseMapper.baseGetById\"/>");
        printLine("</select>");
    }

    private static void getPoByIdListSql(Class<?> cls) {
        printLine("<!-- get Po by idList-->");
        printLine("<select id=\"getPoByIdList\" resultMap=\"resultMap\">");
        printLine("    <include refid=\"com.best.publishing.baseMapper.baseGetByIdList\"/>");
        printLine("</select>");
    }

    private static void searchPo() {
        printLine("<!-- search Po -->");
        printLine("<select id=\"searchPo\" resultMap=\"resultMap\">");
        printLine("    select o.* from");
        printLine("    <include refid=\"tableSection\" />");
        printLine("    o");
        printLine("    <include refid=\"selectSection\" />");
        printLine("</select>");
    }

    private static void searchPoCount() {
        printLine("<!-- search Po count-->");
        printLine("<select id=\"searchCount\" resultType=\"java.lang.Long\" >");
        printLine("    select count(o.id) from");
        printLine("    <include refid=\"tableSection\" />");
        printLine("    o");
        printLine("    <include refid=\"selectSection\" />");
        printLine("</select>");
    }

    private static void generalSearchWhereClause() {
        printLine("<!-- general search clause-->");
        printLine("<sql id=\"selectSection\">");
        printLine("   <!-- common -->");
        printLine("   <include refid=\"com.best.publishing.baseMapper.searchPoFromCommonSection\"/>");
        printWhiteLine();
        printLine("   <!-- please join other table here if necessary-->");
        printWhiteLine();
        printWhiteLine();
        printWhiteLine();
        printLine("   <where>");
        printLine("         <include refid=\"com.best.publishing.baseMapper.searchPoWhereJoinSection\"/>");
        printLine("         <include refid=\"com.best.publishing.baseMapper.searchPoWhereCommonSection\"/>");
        printWhiteLine();
        printLine("         <!-- please add other clause here if necessary-->");
        printWhiteLine();
        printWhiteLine();
        printWhiteLine();
        printWhiteLine();
        printLine("   </where>");
        printLine("</sql>");
    }

    private static void resultMap(String mybatisPoClassPath, Class<?> cls) {
        printLine("<!-- result map-->");
        printLine("<resultMap id=\"resultMap\" type=\"" + mybatisPoClassPath + "\" extends=\"com.best.publishing.baseMapper.baseResultMap\">");
        Field[] fieldlist = cls.getDeclaredFields();
        // <result column="TREEPATH" jdbcType="VARCHAR" property="treePath" />
        int baseFieldCount = 18;
        int j = 0;
        for (int i = 0; i < fieldlist.length; i++) {
            Field fld = fieldlist[i];
            if (isContinueField(fld)) {
                continue;
            }

            String fieldName = fld.getName();
            int index = baseFieldCount + j;
            j++;
            String fieldIndex = "      <!--" + index + "-->";

            String oracleFieldName = "";
            String mybatisPoFieldName = fieldName;
            oracleFieldName = getSqlFieldName(fieldName);
            String oracleFieldType = getOracleFieldType(fld);
            printLine(fieldIndex + "<result column=\"" + oracleFieldName + "\" jdbcType=\"" + oracleFieldType + "\" property=\"" + mybatisPoFieldName + "\" />");
        }
        printLine("</resultMap>");
    }

    @SuppressWarnings("unused")
    private static String getMybatisClassPath(String hibernateClassPath, String mybatisPoClassName) {
        int lastDotIndex = hibernateClassPath.lastIndexOf(".");
        hibernateClassPath = hibernateClassPath.substring(0, lastDotIndex + 1) + mybatisPoClassName;
        return hibernateClassPath.replace("genidc", "wms");
    }

    private static void printLine(String printString) {
        System.out.println(printString);
    }

    private static void printWhiteLine() {
        System.out.println("");
    }

    private static String getOracleFieldType(Field fld) {
        String oracleFieldType = "";
//        if (fld.getAnnotation(ManyToOne.class) != null) {
//            oracleFieldType = "DECIMAL";
//        }

        String fieldType = fld.getType().toString();
        if ("class java.lang.Long".equals(fieldType)) {
            oracleFieldType = "DECIMAL";
        } else if ("class java.lang.String".equals(fieldType)) {
            oracleFieldType = "VARCHAR";
        } else if ("class java.lang.Double".equals(fieldType)) {
            oracleFieldType = "FLOAT";
        } else if ("class java.util.Date".equals(fieldType)) {
            oracleFieldType = "TIMESTAMP";
        } else if ("class java.lang.Boolean".equals(fieldType)) {
            oracleFieldType = "BIT";
        } else if ("class java.lang.Integer".equals(fieldType)) {
            oracleFieldType = "INTEGER";
        } else if ("int".equals(fieldType)) {
            oracleFieldType = "INTEGER";
        } else if ("boolean".equals(fieldType)) {
            oracleFieldType = "BIT";
        }
        return oracleFieldType;
    }

    private static boolean isContinueField(Field fld) {
        boolean isContinueField = false;
        String fieldName = fld.getName();
        if (excludeFidldList.contains(fieldName.toUpperCase())) {
            isContinueField = true;
        }
//        Transient transientAnnotation = fld.getAnnotation(Transient.class);
//        if (transientAnnotation != null) {
//            isContinueField = true;
//        }
        return isContinueField;
    }

    private static String lastValidField(Field[] fieldlist) {
        String lastValidField = "";
        for (int i = 0; i < fieldlist.length; i++) {
            if (!isContinueField(fieldlist[i])) {
                lastValidField = fieldlist[i].getName();
            }
        }
        return lastValidField;
    }
}
