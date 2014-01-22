package com.jivesoftware.community.betterbookmarks.rest.impl;

import com.google.common.collect.Maps;
import com.jivesoftware.base.User;
import com.jivesoftware.community.EntityDescriptor;
import com.jivesoftware.community.JiveObject;
import com.jivesoftware.community.UserAuthoredObject;
import com.jivesoftware.community.browse.rest.ItemBeanBuilder;
import com.jivesoftware.community.browse.rest.ItemBeanPropertyProvider;
import com.jivesoftware.community.browse.rest.impl.AbstractItemBeanBuilder;
import com.jivesoftware.community.browse.rest.impl.JiveObjectAuthorProviderImpl;
import com.jivesoftware.community.browse.rest.impl.JiveObjectLastActivityDateProviderImpl;
import com.jivesoftware.community.favorites.Favorite;
import com.jivesoftware.community.favorites.ObjectFavorite;
import com.jivesoftware.community.favorites.rest.BookmarkBean;
import com.jivesoftware.community.favorites.rest.impl.BookmarkItemTagInfoProviderImpl;
import com.jivesoftware.community.user.rest.UserItemBean;
import com.jivesoftware.community.util.BasePermHelper;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;

import java.util.*;

/*
 * $Revision$
 * $Date$
 *
 * Copyright (C) 1999-2011 Jive Software. All rights reserved.
 *
 * This software is the proprietary information of Jive Software. Use is subject to license terms.
 */
import com.google.common.collect.Maps;
import com.jivesoftware.base.User;
import com.jivesoftware.community.EntityDescriptor;
import com.jivesoftware.community.JiveObject;
import com.jivesoftware.community.UserAuthoredObject;
import com.jivesoftware.community.browse.rest.ItemBeanBuilder;
import com.jivesoftware.community.browse.rest.ItemBeanPropertyProvider;
import com.jivesoftware.community.browse.rest.impl.AbstractItemBeanBuilder;
import com.jivesoftware.community.browse.rest.impl.JiveObjectAuthorProviderImpl;
import com.jivesoftware.community.browse.rest.impl.JiveObjectLastActivityDateProviderImpl;
import com.jivesoftware.community.favorites.Favorite;
import com.jivesoftware.community.favorites.ObjectFavorite;
import com.jivesoftware.community.favorites.rest.BookmarkBean;
import com.jivesoftware.community.user.rest.UserItemBean;
import com.jivesoftware.community.util.BasePermHelper;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * A builder for bookmark beans.
 *
 * @since Jive SBS 5.0
 */
public class BookmarkItemBeanBuilderImpl extends AbstractItemBeanBuilder<Favorite, BookmarkBean> {

    private static Logger log = LogManager.getLogger(BookmarkItemBeanBuilderImpl.class);
    private ItemBeanBuilder<User, UserItemBean> userItemBeanBuilder;

    public static final String YOUR_BOOKMARKS = "yourBookmarks";
    public static final String ALL_BOOKMARKS_BY_ACTIVITY = "allBookmarksByActivity";

    @Required
    public void setUserItemBeanBuilder(ItemBeanBuilder<User, UserItemBean> userItemBeanBuilder) {
        this.userItemBeanBuilder = userItemBeanBuilder;
    }

    @Override
    public Collection<String> getDefaultItemBeanProperties() {
        return BookmarkBean.DEFAULT_BROWSE_VIEW_PROPERTIES;
    }

    @Override
    protected BookmarkBean itemBeanFactory(Favorite object) {
        return new BookmarkBean(object);
    }

    @Override
    public Collection<BookmarkBean> build(Collection<Favorite> objects, User user, Collection<String> providerProps, Map<String, Object> additionalContext) {
        Map<EntityDescriptor,Favorite> edToObjects = Maps.newHashMap();
        for (Favorite favorite : objects) {
            edToObjects.put(new EntityDescriptor(favorite.getObjectType(), favorite.getID()),favorite);
        }
        Map<String, ItemBeanPropertyProvider<Favorite, Object>> providers = getEffectiveProviderMap(providerProps, objects);

        Collection<BookmarkBean> beans = super.build(objects,user, Collections.<String> emptySet(),additionalContext);
        for (BookmarkBean bean : beans) {
            Favorite favorite = edToObjects.get(new EntityDescriptor(bean.getType(), Long.parseLong(bean.getID())));
            doCustomBuild(favorite,bean,user,providers.values(), additionalContext);
        }
        return beans;

    }

    private void doCustomBuild(Favorite favorite, BookmarkBean itemBean, User user,
                               Collection<ItemBeanPropertyProvider<Favorite, Object>> providers, Map<String, Object> additionalContext) {

        if (additionalContext == null) additionalContext = new HashMap<String,Object>();

        ObjectFavorite target = favorite.getObjectFavorite();

        itemBean.setCanEdit(BasePermHelper.isSystemAdmin(user) || user.getID() == (favorite.getCreator().getID()));

        if (favorite.getObjectFavorite().getFavoritedObject() instanceof UserAuthoredObject){
            // if bookmark of internal content, always set the author to the author of the content
            populateAuthor(((UserAuthoredObject)(favorite.getObjectFavorite().getFavoritedObject())).getUser(), itemBean);
        } else {
            // fallback to the creator
            populateAuthor((User) favorite.getCreator(), itemBean);
        }
        //MATT
        itemBean.setThumbnailTemplate("jive.browse.betterbookmark.thumbnailBookmarkedItem");
        itemBean.setDetailTemplate("jive.browse.betterbookmark.detailBookmarkedItem");
        //System.out.println("BookmarkItemBeanBuilderImpl from doCustomBuild");


        // apply prop providers to favorited object
        for (ItemBeanPropertyProvider provider : providers) {
            try {
                additionalContext.put("favorite", favorite);
                Date modificationDate = favorite.getModificationDate();
                if (modificationDate != null) {
                    additionalContext.put(JiveObjectLastActivityDateProviderImpl.PROPERTY_NAME,
                            modificationDate.getTime());
                }
                Object value = null;
                if (provider.propertyName().equals(BookmarkItemTagInfoProviderImpl.PROPERTY_NAME)) {
                    if (evaluateBooleanProperty(additionalContext.get(YOUR_BOOKMARKS))) {
                        itemBean.setYourBookmarks((Boolean) additionalContext.get(YOUR_BOOKMARKS));
                        value = provider.provideProperty(favorite, user, additionalContext);
                    }
                }
                else {
                    value = provider.provideProperty(target.<JiveObject> getFavoritedObject(), user, additionalContext);
                }
                if (value != null) {
                    itemBean.getProp().put(provider.propertyName(), value);
                }
            }
            catch (Exception e) {
                log.warn("Failed to provide property to " + favorite + " from provider " + provider, e);
            }
        }

    }

    private void populateAuthor(User user, BookmarkBean itemBean) {
        itemBean.getProp().put(
                JiveObjectAuthorProviderImpl.PROPERTY_NAME,
                userItemBeanBuilder.build(user, null, UserItemBean.DEFAULT_USER_PROPERTIES,
                        null));
    }

}
