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
            console.log("main - init called.");
            var main = this;

            //View
            main.managedView = this.createManagedView();

            //Model
            main.mnagedSource = this.createManagedSource();

            /**/
            main.managedView.addListener('managed-create', function(managedName) {
                    //console.log("main - addListener(managed-create) called.");

                    main.mnagedSource.addManagedBookmark(managedName);
            });




        };

        protect.createManagedView = function() {
            return new ManagedView();
        }

        protect.createManagedSource = function(){
            return new ManagedSource();
        };

    });
});

