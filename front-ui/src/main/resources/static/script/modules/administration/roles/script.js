/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

url_transaction = "storerole";
url_transaction_update = "updaterole";
url_transaction_delete = "deleterole"

url_transaction_createroleprivilege = "createroleprivilege"
url_transaction_deleteroleprivilege = "deleteroleprivilege"

$(function () {


    $('#ajouter').click(function () {
        //alert("Handler for .click() called.");
        $('#add-form #name').val('');
        $("#savebtn").prop("disabled", false);
    });



    $('#add-form').submit(function (e) {
        e.preventDefault();
        $("#savebtn").prop("disabled", true);
        var str_name = $('#add-form #name').val();

        $('#messageinfos').empty().html('<div class="card-body loader-demo d-flex align-items-center justify-content-center"><div class="ball-pulse"><div></div><div></div><div></div></div></div>');
        addrole(str_name);

    });


    $('table#datatable2').on("click", "button.btn", function (e) {
        var myId = $(this).attr('id');
        var myvalue = $(this).attr('value');
        switch (myId) {
            case "editer":
                $("#editsavebtn").prop("disabled", false);
                getroleById(myvalue);
                break;

            case "afficherprivilege":
                window.location = 'afficherprivilege?idrole=' + myvalue;
                break;
            case "supprimer":
                swal({
                    title: "Demande de Confirmation",
                    text: "Etes-vous s\373r de vouloir supprimer cette donn\351e?'",
                    icon: "warning",
                    buttons: {
                        cancel: {
                            text: "Non, Annuler Operation!",
                            value: null,
                            visible: !0,
                            className: "",
                            closeModal: !1
                        },
                        confirm: {
                            text: "Oui, Confirmer la Suppresion!",
                            value: !0,
                            visible: !0,
                            className: "bg-danger",
                            closeModal: !1
                        }
                    }
                }).then(function (e) {
                    e ? deleterole(myvalue)
                            : swal("Cancelled", "Op\351ration annul\351e:)", "error")
                });


                break;
        }
    });

    $('input[type=checkbox]').change(function () {
        var idrole = $('#cardDemo14 #idrole').val();
        //alert(idrole);
        if ($(this).is(':checked')) {
            //alert("ischeck");
            //alert($(this).attr('id'));
            addroleprivilege(idrole,$(this).attr('id'));
        } else {
            //alert("is Uncheck");
            //alert($(this).attr('id'));
            deleteroleprivilege(idrole,$(this).attr('id'))
        }
    });



    $('#edit-form').submit(function (e) {
        e.preventDefault();
        $("#editsavebtn").prop("disabled", true);
        var str_name = $('#edit-form #name_edit').val();
        var idrole = $('#edit-form #idrole').val();

        $('#messageinfos_edit').empty().html('<div class="card-body loader-demo d-flex align-items-center justify-content-center"><div class="ball-pulse"><div></div><div></div><div></div></div></div>');
        updaterole(idrole, str_name);

    });

});


function addrole(str_name) {
    $.ajax({
        url: url_transaction, // La ressource cibl\351e
        type: 'POST', // Le type de la requ�te HTTP.
        data: 'name=' + str_name,
        dataType: 'text',
        success: function (json) {
            var obj = $.parseJSON(json);
            var data = obj[0];
            if (data.results == "1")
            {
                $('#messageinfos').empty();
                swal({
                    title: "Op\351ration r\351ussie!",
                    text: data.msg,
                    icon: "success",
                    confirmButtonText: "Ok"
                });
                $('.modal').modal('hide');
                setTimeout(redirectpage, 2000);
            } else {
                $('#messageinfos').empty();
                swal({
                    title: "Echec de l'op\351raion",
                    text: data.msg,
                    type: "error",
                    confirmButtonText: "Ok"
                });
                $("#savebtn").prop("disabled", false);
            }
        }
    });
}


function updaterole(idrole, str_name) {
    $.ajax({
        url: url_transaction_update, // La ressource cibl\351e
        type: 'PATCH', // Le type de la requ�te HTTP.
        data: 'name=' + str_name + '&idrole=' + idrole,
        dataType: 'text',
        success: function (json) {
            var obj = $.parseJSON(json);
            var data = obj[0];
            if (data.results == "1")
            {
                $('#messageinfos_edit').empty();
                swal({
                    title: "Op\351ration r\351ussie!",
                    text: data.msg,
                    icon: "success",
                    confirmButtonText: "Ok"
                });
                $('.modal').modal('hide');
                setTimeout(redirectpage, 2000);
            } else {
                $('#messageinfos_edit').empty();
                swal({
                    title: "Echec de l'op\351raion",
                    text: data.msg,
                    type: "error",
                    confirmButtonText: "Ok"
                });
                $("#editsavebtn").prop("disabled", false);
            }
        }
    });
}


function deleterole(idrole) {
    $.ajax({
        url: url_transaction_delete, // La ressource cibl\351e
        type: 'POST', // Le type de la requ�te HTTP.
        data: 'idrole=' + idrole,
        dataType: 'text',
        success: function (json) {

            var obj = $.parseJSON(json);
            var data = obj[0];
            if (data.results == "1")
            {
                //swal("Op\351ration r\351ussie!", "Suppresion effetu\351e avec succes.", "success")
                swal({
                    title: "Op\351ration r\351ussie!",
                    text: data.msg,
                    icon: "success",
                    confirmButtonText: "Ok"

                });
                $('.modal').modal('hide');
                setTimeout(redirectpage, 2000);
            } else {
                swal({
                    title: "Echec de l'op\351raion",
                    text: data.msg,
                    type: "error",
                    confirmButtonText: "Ok"
                });
                $('.modal').modal('hide');
            }
        }
    });

}


function getroleById(idrole)
{
    $.get("findrole/" + idrole, function (json)
    {
        //alert(json);
        if (json.recordsTotal == "1")
        {
            //alert(json.recordsTotal);
            var results = json.results;
            if (results.length > 0)
            {
                $.each(results, function (i, value)
                {
                    $('#edit-form #name_edit').val(results[i].name);
                    $('#edit-form #idrole').val(results[i].id);

                });
            }
        }
    });
}



function addroleprivilege(idrole,idprivilege) {
    $.ajax({
        url: url_transaction_createroleprivilege, // La ressource cibl\351e
        type: 'POST', // Le type de la requ�te HTTP.
        data: 'idrole=' + idrole + '&idprivilege=' + idprivilege,
        dataType: 'text',
        success: function (json) {
            var obj = $.parseJSON(json);
            var data = obj[0];
            if (data.results == "1")
            {
                swal({
                    title: "Op\351ration r\351ussie!",
                    text: data.msg,
                    icon: "success",
                    confirmButtonText: "Ok"
                });
                $('.modal').modal('hide');
              
            } else {
                swal({
                    title: "Echec de l'op\351raion",
                    text: data.msg,
                    type: "error",
                    confirmButtonText: "Ok"
                });
            
            }
        }
    });
}

function deleteroleprivilege(idrole,idprivilege) {
    $.ajax({
        url: url_transaction_deleteroleprivilege, // La ressource cibl\351e
        type: 'POST', // Le type de la requ�te HTTP.
        data: 'idrole=' + idrole +'&idprivilege='+idprivilege,
        dataType: 'text',
        success: function (json) {

            var obj = $.parseJSON(json);
            var data = obj[0];
            if (data.results == "1")
            {
                //swal("Op\351ration r\351ussie!", "Suppresion effetu\351e avec succes.", "success")
                swal({
                    title: "Op\351ration r\351ussie!",
                    text: data.msg,
                    icon: "success",
                    confirmButtonText: "Ok"

                });
                $('.modal').modal('hide');
            } else {
                swal({
                    title: "Echec de l'op\351raion",
                    text: data.msg,
                    type: "error",
                    confirmButtonText: "Ok"
                });
                $('.modal').modal('hide');
            }
        }
    });

}



function redirectpage() {
    window.location = 'roles';
}