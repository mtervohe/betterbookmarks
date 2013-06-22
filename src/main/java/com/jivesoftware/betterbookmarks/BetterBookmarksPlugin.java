package com.jivesoftware.betterbookmarks;

import com.jivesoftware.base.plugin.Plugin;
import com.jivesoftware.community.JiveContainer;
import com.jivesoftware.community.aaa.authz.EntitlementTypeProvider;
import com.jivesoftware.community.objecttype.EntitlementCheckProvider;
import com.jivesoftware.community.places.PlaceTabLink;
import org.apache.log4j.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: matthew.hernandez
 * Date: 5/21/13
 * Time: 8:46 PM
 * To change this template use File | Settings | File Templates.
 */
public class BetterBookmarksPlugin extends PlaceTabLink implements Plugin {
    //Initialize the logger so you can create some logging level messages!
    Logger log = Logger.getLogger(BetterBookmarksPlugin.class);
    /**
     * Method that is called when the application starts up with the plugin.
     */
    public void initPlugin() {
        log.info("Starting the BetterBookmarks Plugin...");
    }


    public void destroy() {
        log.info("Stopping the BetterBookmarks Plugin...");
    }

    //EntitlementCheckProvider<Event> entitlementCheckProvider;

    /*
    @Override
    public boolean isVisible() {
        JiveContainer container = uiComponentContext.getContainer();
        return entitlementCheckProvider.isUserEntitled(container, EntitlementTypeProvider.EntitlementType.VIEW);
    }
    */

    @Override
    public String getUrl() {
        JiveContainer container = uiComponentContext.getContainer();
        return url + "/" + container.getName() + "/betterbookmarks.jspa";
    }


    /*
    public void setEntitlementCheckProvider(EntitlementCheckProvider<Event> entitlementCheckProvider) {
        this.entitlementCheckProvider = entitlementCheckProvider;
    }
    */




}
