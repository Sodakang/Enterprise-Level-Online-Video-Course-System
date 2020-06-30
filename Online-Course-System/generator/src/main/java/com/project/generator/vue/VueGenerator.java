package com.project.generator.vue;

import com.project.generator.util.DbUtil;
import com.project.generator.util.Field;
import com.project.generator.util.FreemarkerUtil;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.*;

public class VueGenerator {

    private static String MODULE = "business";

    private static String toVuePath = "admin//src//views//admin//";

    private static String generatorConfigPath = "server//src//main//resources//generator//generatorConfig.xml";

    /**
     * Get all the Java types and use set to remove the duplicates.
     */
    private static Set<String> getJavaTypes(List<Field> fieldList) {
        Set<String> set = new HashSet<>();
        for (int i = 0; i < fieldList.size(); i++) {
            Field field = fieldList.get(i);
            set.add(field.getJavaType());
        }
        return set;
    }

    public static void main(String[] args) throws Exception {
        String module = MODULE;

        // Generate the first table in the configuration file.
        File file = new File(generatorConfigPath);
        SAXReader reader=new SAXReader();
        // Read the xml file into document.
        Document doc=reader.read(file);
        // Get the root element of the xml file.
        Element rootElement=doc.getRootElement();
        // Get the context element.
        Element contextElement = rootElement.element("context");
        // Define an element for iteration.
        Element tableElement;
        // Get the first table element.
        tableElement=contextElement.elementIterator("table").next();
        String Domain = tableElement.attributeValue("domainObjectName");
        String tableName = tableElement.attributeValue("tableName");
        String domain = Domain.substring(0, 1).toLowerCase() + Domain.substring(1);
        System.out.println("Table: "+tableElement.attributeValue("tableName"));
        System.out.println("Domain: "+tableElement.attributeValue("domainObjectName"));

        List<Field> fieldList = DbUtil.getColumnByTableName(tableName);
        Set<String> typeSet = getJavaTypes(fieldList);
        Map<String, Object> map = new HashMap<>();
        map.put("Domain", Domain);
        map.put("domain", domain);
        map.put("module", module);
        map.put("fieldList", fieldList);
        map.put("typeSet", typeSet);

        // Generate dto.
        FreemarkerUtil.initConfig("vue.ftl");
        FreemarkerUtil.generator(toVuePath + domain + ".vue", map);

    }
}
