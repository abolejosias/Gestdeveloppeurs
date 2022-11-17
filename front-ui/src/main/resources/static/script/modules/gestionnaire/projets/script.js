/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

url_transaction = "storeprojets";
url_transaction_update = "updateprojets";
url_transaction_delete = "deleteprojets"

url_transaction_projectmember = "createprojectmember"
url_transaction_deleteprojectmember = "deleteprojectmember"

$(function () {
//alert('test');

    $('#ajouter').click(function () {
        //alert("Handler for .click() called.");
        $('#add-form #name').val('');
        $('#add-form #description').val('');
        $("#savebtn").prop("disabled", false);
    });



    $('#add-form').submit(function (e) {
        e.preventDefault();
        $("#savebtn").prop("disabled", true);
        var name = $('#add-form #name').val();
        var description = $('#add-form #description').val();
        $('#messageinfos').empty().html('<div class="card-body loader-demo d-flex align-items-center justify-content-center"><div class="ball-pulse"><div></div><div></div><div></div></div></div>');
        addprojet(name,description);

    });


    $('table#datatable2').on("click", "button.btn", function (e) {
        var myId = $(this).attr('id');
        var myvalue = $(this).attr('value');
        switch (myId) {
            case "editer":
                $("#editsavebtn").prop("disabled", false);
                getprojetsById(myvalue);
                break;
             case "ajouterdeveloppeurs":
                window.location = 'affecterdeveloppeurs?idprojets=' + myvalue;
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
                    e ? deleteprojets(myvalue)
                            : swal("Cancelled", "Op\351ration annul\351e:)", "error")
                });


                break;
        }
    });


 $('input[type=checkbox]').change(function () {
        var idprojets = $('#cardDemo14 #idprojets').val();
        //alert(iddeveloppeur);
        if ($(this).is(':checked')) {
            //alert("ischeck");
            //alert($(this).attr('id'));
            addprojectmember(idprojets,$(this).attr('id'));
        } else {
            //alert("is Uncheck");
            //alert($(this).attr('id'));
            deleteprojectmember(idprojets,$(this).attr('id'))
        }
    });


    $('#edit-form').submit(function (e) {
        e.preventDefault();
        $("#editsavebtn").prop("disabled", true);
        var description_edit = $('#edit-form #description_edit').val();
        var idprojets = $('#edit-form #idprojets').val();

        $('#messageinfos_edit').empty().html('<div class="card-body loader-demo d-flex align-items-center justify-content-center"><div class="ball-pulse"><div></div><div></div><div></div></div></div>');
        updateprojets(idprojets, description_edit);

    });

});


function addprojet(name,description) {
  
    $.ajax({
        url: url_transaction, // La ressource cibl\351e
        type: 'POST', // Le type de la requ�te HTTP.
        data: 'name=' + name + '&description=' + description,
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


function updateprojets(idprojets, description) {
    //alert(idmodule);
    $.ajax({
        url: url_transaction_update, // La ressource cibl\351e
        type: 'PUT', // Le type de la requ�te HTTP.
        data: 'description=' + description + '&idprojets=' + idprojets,
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


function deleteprojets(idprojets) {
    $.ajax({
        url: url_transaction_delete, // La ressource cibl\351e
        type: 'POST', // Le type de la requ�te HTTP.
        data: 'idprojets=' + idprojets,
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


function getprojetsById(idprojets)
{
    $.get("findprojets/" + idprojets, function (json)
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
                    $('#edit-form #name_edit').val(results[i].name);
                    $('#edit-form #description_edit').val(results[i].description);
                    $('#edit-form #idprojets').val(results[i].id);

                });
            }
        }
    });
}




function addprojectmember(idprojets,iddeveloper) {
    $.ajax({
        url: url_transaction_projectmember, // La ressource cibl\351e
        type: 'POST', // Le type de la requ�te HTTP.
        data: 'idprojets=' + idprojets + '&iddeveloper=' + iddeveloper,
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

function deleteprojectmember(idprojets,iddeveloper) {
    $.ajax({
        url: url_transaction_deleteprojectmember, // La ressource cibl\351e
        type: 'POST', // Le type de la requ�te HTTP.
        data: 'idprojets=' + idprojets +'&iddeveloper='+iddeveloper,
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
    window.location = 'projets';
}