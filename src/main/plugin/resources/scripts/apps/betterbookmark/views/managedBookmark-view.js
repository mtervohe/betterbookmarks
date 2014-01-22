/*
 * @depends path=/resources/scripts/lib/jiverscripts/src/conc/observable.js
 * @depends path=/resources/scripts/lib/jiverscripts/src/oo/class.js
 * @depends path=/resources/scripts/jquery/jquery.js
 * */
define('apps/betterbookmark/views/managedBookmark-view', ['jquery'],
    function($){

        //jive.conc.observable(this);

        return jive.AbstractView.extend(function(protect) {
        //console.log("view - oo called.");
            protect.init = function(){
                console.log("view - init called.");
                var view = this;

                $('#managed-bookmark-create').click(function(eventObj) {
                    //console.log("view - managed-bookmark-create.click called.");

                    var managedName = $('#managed-bookmark-textarea').val();
                    view.emit('managed-create', managedName);
                    $('#managed-bookmark-textarea').val('');
                    eventObj.preventDefault();
                });

            }

        });
    });

 /*
MATT - THIS FILE IS = to  'jive.supportal.manageSupportGroupApp.manageGroupView'

*/