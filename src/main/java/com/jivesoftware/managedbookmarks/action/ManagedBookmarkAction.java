package com.jivesoftware.managedbookmarks.action;

import com.jivesoftware.community.web.soy.action.AbstractSoyModelDrivenAction;
import com.jivesoftware.managedbookmarks.ManagedBookmarkManager;
import com.jivesoftware.managedbookmarks.impl.ManagedBookmarkImpl;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: matthew.hernandez
 * Date: 2/9/14
 * Time: 2:09 PM
 * To change this template use File | Settings | File Templates.
 */
public class ManagedBookmarkAction extends AbstractSoyModelDrivenAction {

    static Logger log = LogManager.getLogger(ManagedBookmarkAction.class);

    private ManagedBookmarkManager managedBookmarkManager;

    public void setManagedBookmarkManager(ManagedBookmarkManager managedBookmarkManager) {
        log.info("setManagedBookmarkManager - execute ");
        this.managedBookmarkManager = managedBookmarkManager;
    }

    @Override
    public String execute() {
        log.info("ManagedBookmarkAction - execute ");
        //System.out.println("ManagedBookmarkAction - execute");
        //List managed bookmark list
        List<ManagedBookmarkImpl> managedBookmarkList = Lists.newArrayList();
        managedBookmarkList = managedBookmarkManager.getManagedBookmarks();

        Map<String, Object> map = Maps.newHashMap();

        map.put("managedBookmarkList", managedBookmarkList);

        model = map;

        return super.execute();
    }
    public String doAdd(){
        log.info("ManagedBookmarkAction - doAdd");
        //System.out.println("ProfileBetterBookmarksBrowseAction - doAdd");
        return "SUCCESS";
    }
}

