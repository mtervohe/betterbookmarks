/**
 * @depends path=/resources/scripts/jive/rest.js
 * @depends path=/resources/scripts/apps/shared/models/rest_service.js
 */
define('apps/betterbookmark/models/managedBookmark-source', ['jquery'],
    function($){

    return jive.RestService.extend (function(protect, _super) {

        //this.RESOURCE_ENDPOINT = jive.rest.url("/managed-bookmark");
        protect.resourceType = "managed-bookmark";
        protect.pluralizedResourceType = protect.resourceType;

        protect.init = function(options){
            _super.init.call(this, options);

            // <!-- 1 -->
            this.RESOURCE_ENDPOINT = jive.rest.url("/managed-bookmark");
            protect.resourceType = "managed-bookmark";
            protect.pluralizedResourceType = protect.resourceType;

        }

        this.addManagedBookmark = function updateManagedBookmark(bookmarkName) {
            var source = this,
                url = this.RESOURCE_ENDPOINT + '/create/' + bookmarkName,
                data = {bookmarkName: bookmarkName};
            console.log("managedBookmark-source - updateManagedBookmark");
            return source.execute(url, data);
        }

        protect.execute = function(url, data) {

            var source = this,
                promise = new jive.conc.Promise();
            console.log("managedBookmark-source - execute 01");
            $.ajax({
                url: url,
                type: 'POST',
                dataType: 'html',
                data: data,
                error: source.errorCallback(promise, source.errorSaving),
                success: function(data, textStatus, xhr) {
                    promise.emitSuccess(data);
                }

            });

            console.log("managedBookmark-source.xml - execute 02");

            return promise;
        };

    });
});
