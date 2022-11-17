/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

url_transaction = "storedeveloppeurs";
url_transaction_update = "updatedeveloppeurs";
url_transaction_delete = "deletedeveloppeurs"

url_transaction_developpeurskills = "createdevelopperskills"
url_transaction_deletedeveloppeurskills = "deletedevelopperskills"

$(function () {
//alert('test');

    $('#ajouter').click(function () {
        //alert("Handler for .click() called.");
        $('#add-form #first_name').val('');
        $('#add-form #last_name').val('');
        $("#savebtn").prop("disabled", false);
    });



    $('#add-form').submit(function (e) {
        e.preventDefault();
        $("#savebtn").prop("disabled", true);
        var first_name = $('#add-form #first_name').val();
        var last_name = $('#add-form #last_name').val();
        $('#messageinfos').empty().html('<div class="card-body loader-demo d-flex align-items-center justify-content-center"><div class="ball-pulse"><div></div><div></div><div></div></div></div>');
        adddeveloppeur(first_name,last_name);

    });


    $('table#datatable2').on("click", "button.btn", function (e) {
        var myId = $(this).attr('id');
        var myvalue = $(this).attr('value');
        switch (myId) {
            case "editer":
                $("#editsavebtn").prop("disabled", false);
                getdeveloppeurById(myvalue);
                break;
             case "ajoutercompetence":
                window.location = 'affichercompetence?iddeveloper=' + myvalue;
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
                    e ? deletedeveloppeur(myvalue)
                            : swal("Cancelled", "Op\351ration annul\351e:)", "error")
                });


                break;
        }
    });


 $('input[type=checkbox]').change(function () {
        var iddeveloppeur = $('#cardDemo14 #iddeveloppeur').val();
        //alert(iddeveloppeur);
        if ($(this).is(':checked')) {
            //alert("ischeck");
            //alert($(this).attr('id'));
            adddeveloppeurskills(iddeveloppeur,$(this).attr('id'));
        } else {
            //alert("is Uncheck");
            //alert($(this).attr('id'));
            deletedeveloppeurskills(iddeveloppeur,$(this).attr('id'))
        }
    });


    $('#edit-form').submit(function (e) {
        e.preventDefault();
        $("#editsavebtn").prop("disabled", true);
        var first_name_edit = $('#edit-form #first_name_edit').val();
        var last_name_edit = $('#edit-form #last_name_edit').val();
        var iddeveloppeur = $('#edit-form #iddeveloppeur').val();

        $('#messageinfos_edit').empty().html('<div class="card-body loader-demo d-flex align-items-center justify-content-center"><div class="ball-pulse"><div></div><div></div><div></div></div></div>');
        updatedeveloppeur(iddeveloppeur, first_name_edit,last_name_edit);

    });

});


function adddeveloppeur(first_name,last_name) {
  
    $.ajax({
        url: url_transaction, // La ressource cibl\351e
        type: 'POST', // Le type de la requ�te HTTP.
        data: 'first_name=' + first_name + '&last_name=' + last_name,
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


function updatedeveloppeur(iddeveloppeurs, first_name,last_name) {
    //alert(idmodule);
    $.ajax({
        url: url_transaction_update, // La ressource cibl\351e
        type: 'PUT', // Le type de la requ�te HTTP.
        data: 'first_name=' + first_name + '&iddeveloppeurs=' + iddeveloppeurs+'&last_name='+last_name,
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


function deletedeveloppeur(iddeveloppeurs) {
    $.ajax({
        url: url_transaction_delete, // La ressource cibl\351e
        type: 'POST', // Le type de la requ�te HTTP.
        data: 'iddeveloppeurs=' + iddeveloppeurs,
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


function getdeveloppeurById(iddeveloppeurs)
{
    $.get("finddeveloppeurs/" + iddeveloppeurs, function (json)
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
                    $('#edit-form #first_name_edit').val(results[i].first_name);
                    $('#edit-form #last_name_edit').val(results[i].last_name);
                    $('#edit-form #iddeveloppeur').val(results[i].id);

                });
            }
        }
    });
}




function adddeveloppeurskills(iddeveloper,idskills) {
    $.ajax({
        url: url_transaction_developpeurskills, // La ressource cibl\351e
        type: 'POST', // Le type de la requ�te HTTP.
        data: 'iddeveloper=' + iddeveloper + '&idskills=' + idskills,
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

function deletedeveloppeurskills(iddeveloper,idskills) {
    $.ajax({
        url: url_transaction_deletedeveloppeurskills, // La ressource cibl\351e
        type: 'POST', // Le type de la requ�te HTTP.
        data: 'iddeveloper=' + iddeveloper +'&idskills='+idskills,
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
    window.location = 'developpeur';
}