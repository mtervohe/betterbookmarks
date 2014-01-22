/**
 * @depends path=/resources/scripts/jive/rest.js
 * @depends path=/resources/scripts/apps/shared/models/rest_service.js
 */
define('apps/betterbookmark/models/managedBookmark-source', ['jquery'],
    function($){

    return jive.RestService.extend (function(protect, _super) {


        //protect.resourceType = "support-product";
        //protect.pluralizedResourceType = protect.resourceType;

        protect.init = function(options){
        //console.log("source - init called.");
            _super.init.call(this, options);

            // <-- 1 -->
            this.RESOURCE_ENDPOINT = jive.rest.url("/managed-bookmark");
            //this.RESOURCE_ENDPOINT = jive.rest.url("/support-product");
        }

        this.addManagedBookmark = function updateManagedBookmark(managedName) {

            var source = this,
                url = this.RESOURCE_ENDPOINT + '/create/' + managedName,
                data = {productName: managedName};

            //console.log("source - addManagedBookmark called.");
            return source.execute(url, data);
        }

        protect.execute = function(url, data) {

            var source = this,
                promise = new jive.conc.Promise();

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

            //console.log("source - execute called.");

            return promise;
        };

    });
});
