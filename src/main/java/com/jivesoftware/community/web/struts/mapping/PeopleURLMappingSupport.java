package com.jivesoftware.community.web.struts.mapping;

import org.apache.struts2.dispatcher.mapper.ActionMapping;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: matthew.hernandez
 * Date: 7/17/13
 * Time: 9:52 PM
 * To change this template use File | Settings | File Templates.
 */
public class PeopleURLMappingSupport extends PeopleURLMapping {

    public void process(String uri, ActionMapping mapping) {
        //System.out.println("PeopleURLMappingSupport called process");

        String[] uriElements = uri.split("/");
        Map params = mapping.getParams();
        if (null == params) {
            params = new HashMap();
        }
        String username = uriElements[2];

        if (uri.startsWith("/people/") && (uri.endsWith("/betterbookmarks") || uri.endsWith("/betterbookmarks/") )) {

            mapping.setName("profile");
            params.put("view", "betterbookmarks");


            params.put("username", username);
            mapping.setNamespace("");
            mapping.setParams(params);
        }
        else {
            super.process(uri,mapping);
        }
    }
}

