/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
url_combo_listmenus = "listdesmenus";
url_transaction = "storesousmenu";
url_transaction_delete = "deletesousmenu";
$(function () {


    $('#ajouter').click(function () {
        //alert("Handler for .click() called.");
        $('#add-form #name').val('');
        $('#add-form #description').val('');
        $('#add-form #int_priority').val('');
        getallMenusComboInput("", "select2-1");
        $("#savebtn").prop("disabled", false);
    });



    $('#add-form').submit(function (e) {
        e.preventDefault();
        $("#savebtn").prop("disabled", true);
        var str_name = $('#add-form #name').val();
        var str_description = $('#add-form #description').val();
        var int_priority = $('#add-form #int_priority').val();
        var idmenu = $('#add-form #select2-1').val();
        if (idmenu == "0") {
            swal({
                title: "Champ Obligatoire",
                text: "Le champ menu est Obligatoire",
                type: "error",
                confirmButtonText: "Ok"
            });
            $("#savebtn").prop("disabled", false);
        } else {
            $('#messageinfos').empty().html('<div class="card-body loader-demo d-flex align-items-center justify-content-center"><div class="ball-pulse"><div></div><div></div><div></div></div></div>');
            addsousmenu(str_name, str_description, int_priority,idmenu);
        }
    });


    $('table#datatable2').on("click", "button.btn", function (e) {
        var myId = $(this).attr('id');
        var myvalue = $(this).attr('value');
        switch (myId) {
            case "editer":
                $("#editsavebtn").prop("disabled", false);
              
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
                    e ? deletesousmenu(myvalue)
                     : swal("Cancelled", "Op\351ration annul\351e:)", "error")
                });


                break;
        }
    });

});



function getallMenusComboInput(id_menuselect, id_combo)
{
    $.get(url_combo_listmenus, function (json, textStatus)
    {
        var results = json.results;
        if (json.recordsTotal > 0)
        {
            var oneelement = "<option class='option-item-offer'  value='0'>Choisir un menu</option>";
            $('#' + id_combo).empty().append(oneelement);
            $.each(results, function (i, value)
            {
                var select = (results[i].id == id_menuselect) ? "selected" : "";
                var option = $('<option class="option-item-offer "' + select + '  value="' + results[i].id + '">' + results[i].name + '</option>');
                $('#' + id_combo).append(option);
            });
        }
        $('#' + id_combo).chosen({no_results_text: "Oops, Aucun resultat trouvé!"});
    });
}

function addsousmenu(str_name, str_description, int_priority,idmenu) {
    $.ajax({
        url: url_transaction, // La ressource cibl\351e
        type: 'POST', // Le type de la requ�te HTTP.
        data: 'str_value=' + str_name + '&str_description=' + str_description + '&int_priority=' + int_priority+'&idmenu='+idmenu,
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




function deletesousmenu(idsousmenu) {
    $.ajax({
        url: url_transaction_delete, // La ressource cibl\351e
        type: 'POST', // Le type de la requ�te HTTP.
        data: 'idsousmenu=' + idsousmenu,
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




function redirectpage() {
    window.location = 'sousmenus';
}