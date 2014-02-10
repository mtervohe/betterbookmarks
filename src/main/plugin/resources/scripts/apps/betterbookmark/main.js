//Calling define with module ID, dependency array, and factory function
/**
 * @depends path=/resources/scripts/lib/jiverscripts/src/oo/class.js
 * @depends path=/plugins/betterbookmarks/resources/scripts/apps/betterbookmark/views/managedBookmark-view.js
 * @depends path=/plugins/betterbookmarks/resources/scripts/apps/betterbookmark/models/managedBookmark-source.js
 */
define('apps/betterbookmark/main',['apps/betterbookmark/views/managedBookmark-view',
    'apps/betterbookmark/models/managedBookmark-source'],
    function(ManagedView, ManagedSource) {

    return jive.oo.Class.extend(function(protect) {

        protect.init = function(M) {
            //console.log("ManagedBookmark main - init called.");
            var main = this;

            main.managedBookmarkRestSource = this.createManagedBookmarkRestSource();
            main.managedBookmarkView = this.createManagedBookmarkView();

            main.managedBookmarkView.addListener('managedBookmark-create', function(bookmarkName) {
                    main.managedBookmarkRestSource.addManagedBookmark(bookmarkName);
                    //TO DO: Reload full page for now, reload only list via ajax later
                    main.managedBookmarkView.reloadPage();
            });

        };

        protect.createManagedBookmarkView = function() {
            return new ManagedView();
        }

        protect.createManagedBookmarkRestSource = function(){
            return new ManagedSource();
        };

    });
});

