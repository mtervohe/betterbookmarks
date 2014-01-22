package com.jivesoftware.betterbookmarks.rest;

import com.jivesoftware.betterbookmarks.rest.impl.BetterBookmarkBean;
import com.jivesoftware.community.favorites.rest.impl.BookmarkItemsBean;

import javax.ws.rs.*;
import java.util.Collection;

/**
 * A REST-style service layer for Bookmarks.
 *
 * @since Jive SBS 5.0
 */

@Produces("application/json")
@Path("/betterbookmarks")
public interface BetterBookmarkService {

    /**
     * Create a new bookmark.
     *
     * @param bookmark Bean representing the bookmark to create.
     * @return A bean representing the bookmark as it was saved.
     */
    @POST
    @Path("/")
    public BetterBookmarkBean createBetterBookmark(BetterBookmarkBean bookmark);

    /**
     * Get bookmarks for a given user or all users.
     *
     * @param userID The user for whom to get bookmarks, or "all" for all users.
     * @param filterGroupID The ID of the filter group of the applied filters.
     * @return A collection of Bookmarks.
     */
    @GET
    @Path("/")
    public BookmarkItemsBean getBookmarks(
            @QueryParam("userID") @DefaultValue("-1") long userID,
            @QueryParam("filterGroupID") @DefaultValue("bookmarks") String filterGroupID,
            @QueryParam("filterID") Collection<String> filterID,
            @QueryParam("query") @DefaultValue("") String query,
            @QueryParam("sortKey") @DefaultValue("") String sortKey,
            @QueryParam("sortOrder") @DefaultValue("-1") int sortOrder,
            @QueryParam("start") @DefaultValue("0") int start,
            @QueryParam("numResults") @DefaultValue("20") int numResults);

    /**
     * Deletes a bookmark for the provided ID.
     *
     * @param bookmarkID the ID of the bookmark to delete.
     */
    @DELETE
    @Path("/{bookmarkID}")
    public void deleteBookmark(@PathParam("bookmarkID") long bookmarkID);

}

