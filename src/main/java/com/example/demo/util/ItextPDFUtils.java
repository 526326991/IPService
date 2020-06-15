package com.example.demo.util;


import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * 用于导出PDF格式工具类
 * <p>
 * <p>
 * 注意：
 * 1.List<?> datas 中定义的类型不许是String类型
 *
 * @author xc
 * @date 2020/5/13
 */
public class ItextPDFUtils {

    private static final Logger log = LoggerFactory.getLogger(Class.class);

    //设置表格占比pdf 112% （112 刚好填满）
    private static final float TABLE_PROPORTION = 112;

    //使用iTextAsian.jar中的字体
    private static final String FONT_NAME = "STSong-Light";
    private static final String FONT_ENCODING = "UniGB-UCS2-H";

    //********* 标题属性 ***********
    private static final int TITLE_FONT_SIZE = 28;
    private static final int TITLE_SPAC_AFTER = 50;
    private static final int TITLE_SPAC_BEFORE = 50;

    private static final String TITLE_DEFAULT_NAME = "title";

    //********* 表头属性 ***********
    private static final int HEADER_FONT_SIZE = 9;

    //********* 表格内容属性 ***********
    private static final int CONTENT_FONT_SIZE = 8;


    /**
     * List -> byte[]
     *
     * @param columns 属性
     * @param titles  标题
     * @param datas   数据集
     * @param title   pdf title
     * @return
     */
    public static byte[] listToPDF(String[] columns, String[] titles, List<?> datas, String title) {
        validateParams(columns, titles, datas);
        boolean validate = validate(datas);
        if (!validate) {
            return null;
        }

        try {
            //构建字节输出流
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            //创建文档
            Document document = new Document();
            PdfWriter.getInstance(document, baos);
            document.open();

            int i = getClassSize(datas);

            PdfPTable table = new PdfPTable(i);
            table.setWidthPercentage(TABLE_PROPORTION);

            //封装标题
            resetTitle(document, title);
            //封装表头
            resetHeader(table, datas, titles);
            //封装表格内容
            resetContent(table, datas, columns);

            //组成一行
            table.completeRow();

            //开启文档分页
            table.setSplitLate(false);
            table.setSplitRows(true);

            //分页带上表头
            table.setHeaderRows(1);

            //把表格添加进文档
            document.add(table);
            document.close();
            baos.close();

            return baos.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
            log.error("使用 ItextPDFUtils 工具类错误");
        }
        return null;
    }

    private static void validateParams(String[] columns, String[] titles, List<?> datas) {
        if (columns.length != titles.length) {
            //throw new BusinessException(ErrorCode.NOT_MATCH_CODE, "请核对入参：columns titles");
        }
        if (EmptyUtils.isNotEmpty(datas)) {
            Class<?> aClass = datas.get(0).getClass();
            Field[] declaredFields = aClass.getDeclaredFields();
            if (declaredFields.length == 0) {
                //throw new BusinessException(ErrorCode.NOT_MATCH_CODE, "结果集的属性为空，不合法");
            }
        }

    }

    /**
     * 校验入参非空
     *
     * @param datas
     */
    private static boolean validate(List<?> datas) {
        if (EmptyUtils.isEmpty(datas)) {
            return false;
        }
        int size = datas.size();
        if (size > 0) {
            return true;
        }
        return false;
    }


    /**
     * 获取 类型属性数量
     *
     * @param datas
     * @return
     */
    private static int getClassSize(List<?> datas) {
        if (EmptyUtils.isNotEmpty(datas)) {
            Object obj = datas.get(0);
            Class<?> aClass = obj.getClass();
            Field[] declaredFields = aClass.getDeclaredFields();
            return declaredFields.length;
        }
        return 0;
    }

    /**
     * 获取表头各属性值
     *
     * @param datas
     * @return
     */
    private static List<String> getClassField(List<?> datas) {
        List<String> fieldStrs = new ArrayList();
        if (EmptyUtils.isNotEmpty(datas)) {
            Object obj = datas.get(0);
            Class<?> aClass = obj.getClass();
            Field[] declaredFields = aClass.getDeclaredFields();
            for (Field declaredField : declaredFields) {
                String name = declaredField.getName();
                fieldStrs.add(name);
            }
        }
        return fieldStrs;
    }

    /**
     * 获取字体
     *
     * @return
     */
    private static Font getFont() throws IOException, DocumentException {
        BaseFont baseFont = BaseFont.createFont(FONT_NAME, FONT_ENCODING, BaseFont.NOT_EMBEDDED);
        Font font = new Font(baseFont);
        return font;
    }


    /**
     * 封装 标题
     *
     * @param document
     * @param title
     * @throws Exception
     */
    private static void resetTitle(Document document, String title) throws Exception {
        if (StringUtils.isEmpty(title)) {
            title = TITLE_DEFAULT_NAME;
        }
        Font font = getFont();
        //标题
        font.setSize(TITLE_FONT_SIZE);
        font.setStyle(Font.BOLD);
        Paragraph p = new Paragraph(title, font);
        //设置标题段落前后距离
        p.setSpacingAfter(TITLE_SPAC_AFTER);
        p.setSpacingBefore(TITLE_SPAC_BEFORE);
        //设置标题对齐方式
        p.setAlignment(Element.ALIGN_CENTER);
        document.add(p);
    }

    /**
     * 封装 表头
     *
     * @param table
     * @param datas
     * @param titles
     */
    private static void resetHeader(PdfPTable table, List<?> datas, String... titles) throws IOException, DocumentException {
        validateTitle(datas, titles);
        int k = titles.length;
        //格子(用来装表头的数据)
        PdfPCell[] cells = new PdfPCell[k];
        Font font = getFont();
        //表头
        for (int i = 0; i < titles.length; i++) {
            font.setSize(HEADER_FONT_SIZE);
            font.setStyle(Font.BOLD);
            cells[i] = new PdfPCell(new Phrase(titles[i], font));
            //设置表头颜色
            cells[i].setBackgroundColor(new BaseColor(110, 213, 60));
            //启用/禁用基于最大升序的第一行高度调整。
            cells[i].setUseAscender(true);
            //设置单元格的垂直对齐方式。
            cells[i].setVerticalAlignment(Element.ALIGN_MIDDLE);
            //设置单元格的水平对齐方式。
            //cells[i].setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cells[i]);
        }
    }

    /**
     * 校验 内容与标题长度是否一致
     *
     * @param datas
     * @param titles
     */
    private static void validateTitle(List<?> datas, String[] titles) {
        int size = getFiledName(datas.get(0)).length;
        int length = titles.length;
        if (size != length) {
            //throw new BusinessException(ErrorCode.MOBILE_FORMAT_ERR_CODE, "入参集合与String[] titles 长度不一致");
        }

    }


    /**
     * 封装内容
     *
     * @param table
     * @param datas
     * @param columns
     */
    private static void resetContent(PdfPTable table, List<?> datas, String... columns) throws IOException, DocumentException {
        Font font = getFont();
        font.setSize(CONTENT_FONT_SIZE);
        font.setStyle(Font.NORMAL);

        for (Object obj : datas) {

            //validateObjectType(obj);
            Class<?> aClass = obj.getClass();
            Field[] declaredFields = aClass.getDeclaredFields();

            Phrase[] ps = new Phrase[columns.length];
            PdfPCell[] pc = new PdfPCell[columns.length];


            //每个类型的属性
            for (int i = 0; i < columns.length; i++) {
                String fieldName = getFiledNameByName(columns[i], declaredFields);
                //ValidatorUtils.checkEmptyThrowEx(fieldName, "入参columns 与属性值不匹配");

                String fileValue = getFieldValueByName(fieldName, obj) + "";
                //实例化
                ps[i] = new Phrase(fileValue, font);
                //把内容放进格子
                pc[i] = new PdfPCell(ps[i]);
                pc[i].setUseAscender(true);

                //把格子放入表格中
                table.addCell(pc[i]);
            }

        }

    }

    /**
     * @param column
     * @param declaredFields
     * @return
     */
    private static String getFiledNameByName(String column, Field... declaredFields) {
        for (int i = 0; i < declaredFields.length; i++) {
            String name = declaredFields[i].getName();
            if (StringUtils.equals(name, column)) {
                return column;
            }
        }
        return null;
    }

    /**
     * 校验 object 对象的属性和类型
     * <p>
     * 暂时只识别 String 类型
     *
     * @param obj
     */
    private static void validateObjectType(Object obj) {
        if (obj instanceof String) {
            //todo:
        } else if (obj instanceof java.math.BigDecimal) {
        } else {
            String typeName = obj.getClass().getTypeName();
            //throw new BusinessException(ErrorCode.MESSAGE_SEND_EXCEPTION_CODE, "目前ItextPDSUtils只支持String，BigDecimal类型,不支持:" + typeName + "类型");
        }
    }

    /**
     * 根据属性名获取属性值
     */
    private static Object getFieldValueByName(String fieldName, Object o) {
        try {
            String firstLetter = fieldName.substring(0, 1).toUpperCase();
            String getter = "get" + firstLetter + fieldName.substring(1);
            Method method = o.getClass().getMethod(getter, new Class[]{});
            Object value = method.invoke(o, new Object[]{});
            return value;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return null;
        }
    }

    /**
     * 获取属性名数组
     */
    private static String[] getFiledName(Object o) {
        Field[] fields = o.getClass().getDeclaredFields();
        String[] fieldNames = new String[fields.length];
        for (int i = 0; i < fields.length; i++) {
            System.out.println(fields[i].getType());
            fieldNames[i] = fields[i].getName();
        }
        return fieldNames;
    }

}