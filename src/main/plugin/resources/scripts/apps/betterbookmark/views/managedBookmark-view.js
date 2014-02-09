/*
 * @depends path=/resources/scripts/lib/jiverscripts/src/conc/observable.js
 * @depends path=/resources/scripts/lib/jiverscripts/src/oo/class.js
 * @depends path=/resources/scripts/jquery/jquery.js
 * */
define('apps/betterbookmark/views/managedBookmark-view', ['jquery'],
    function($){

        return jive.AbstractView.extend(function(protect) {
            protect.init = function(){
                var view = this;

                //Wire up event handlers
                $('#managed-bookmark-create').click(function(eventObj) {
                    var bookmarkName = $('#managed-bookmark-textarea').val();
                    view.emit('managedBookmark-create', bookmarkName);
                    $('#managed-bookmark-textarea').val('');
                    eventObj.preventDefault();
                });

            }

        });
    });

 /*
MATT - THIS FILE IS = to  'jive.supportal.manageSupportGroupApp.manageGroupView'

*/