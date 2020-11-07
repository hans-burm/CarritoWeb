$(document).ready(function () {
    $("tr #btnDelete").click(function () {
        var idp = $(this).parent().find("#idp").val();
        swal({
            title: "Está seguro que desea eliminar?",
            text: "Si lo elimina, Ud puede agregar nuevamente!",
            icon: "warning",
            buttons: true,
            dangerMode: true,
        })
                .then((willDelete) => {
                    if (willDelete) {
                        eliminar(idp);
                        swal("Registro eliminado!!", {
                            icon: "success",
                        }).then((willDelete)=>{
                            parent.location.href="Controlador?accion=Carrito";
                        });
                    } else {
                        swal("Acción cancelada!");
                    }
                });
    });
    function eliminar(idp) {
        var url = "Controlador?accion=Delete";
        $.ajax({
            type: 'POST',
            url: url,
            data: "idp=" + idp,
            success: function (data, textStatus, jqXHR) {
                
            }
        })
    }

});


