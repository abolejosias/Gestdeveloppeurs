/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

url_transaction = "storemodules";
url_transaction_update = "updatemodules";
url_transaction_delete = "deletemodules"


$(function () {


    $('#ajouter').click(function () {
        //alert("Handler for .click() called.");
        $('#add-form #name').val('');
        $('#add-form #description').val('');
        //$('#add-form #p_key').val('');
        $('#add-form #int_priority').val('');
        $("#savebtn").prop("disabled", false);
    });



    $('#add-form').submit(function (e) {
        e.preventDefault();
        $("#savebtn").prop("disabled", true);
        var str_name = $('#add-form #name').val();
        var description = $('#add-form #description').val();
        //var p_key = $('#add-form #p_key').val();
        var int_priority = $('#add-form #int_priority').val();
        $('#messageinfos').empty().html('<div class="card-body loader-demo d-flex align-items-center justify-content-center"><div class="ball-pulse"><div></div><div></div><div></div></div></div>');
        addmodules(str_name,description,int_priority);

    });


    $('table#datatable2').on("click", "button.btn", function (e) {
        var myId = $(this).attr('id');
        var myvalue = $(this).attr('value');
        switch (myId) {
            case "editer":
                $("#editsavebtn").prop("disabled", false);
                getmoduleById(myvalue);
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
                    e ? deletemodules(myvalue)
                            : swal("Cancelled", "Op\351ration annul\351e:)", "error")
                });


                break;
        }
    });



    $('#edit-form').submit(function (e) {
        e.preventDefault();
        $("#editsavebtn").prop("disabled", true);
        var str_name = $('#edit-form #name_edit').val();
        var str_description = $('#edit-form #description_edit').val();
        var int_priority = $('#edit-form #int_priority_edit').val();
        var idmodule = $('#edit-form #idmodule').val();

        $('#messageinfos_edit').empty().html('<div class="card-body loader-demo d-flex align-items-center justify-content-center"><div class="ball-pulse"><div></div><div></div><div></div></div></div>');
        updatemodule(idmodule, str_name,str_description,int_priority);

    });

});


function addmodules(str_name,str_description,int_priority) {
  
    $.ajax({
        url: url_transaction, // La ressource cibl\351e
        type: 'POST', // Le type de la requ�te HTTP.
        data: 'str_name=' + str_name + '&str_description=' + str_description + '&int_priority=' + int_priority,
        dataType: 'text',
        success: function (json) {
            //alert(json);
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


function updatemodule(idmodule, str_name,str_description,int_priority) {
    //alert(idmodule);
    $.ajax({
        url: url_transaction_update, // La ressource cibl\351e
        type: 'PUT', // Le type de la requ�te HTTP.
        data: 'str_name=' + str_name + '&idmodule=' + idmodule+'&str_description='+str_description+'&int_priority='+int_priority,
        dataType: 'text',
        success: function (json) {
            //alert(json);
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


function deletemodules(idmodule) {
    $.ajax({
        url: url_transaction_delete, // La ressource cibl\351e
        type: 'POST', // Le type de la requ�te HTTP.
        data: 'idmodule=' + idmodule,
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


function getmoduleById(idmodule)
{
    $.get("findmodule/" + idmodule, function (json)
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
                    //alert(results[i].id);
                    $('#edit-form #name_edit').val(results[i].str_value);
                    $('#edit-form #description_edit').val(results[i].str_description);
                    $('#edit-form #int_priority_edit').val(results[i].int_priority);
                    $('#edit-form #idmodule').val(results[i].id);

                });
            }
        }
    });
}






function redirectpage() {
    window.location = 'modules';
}