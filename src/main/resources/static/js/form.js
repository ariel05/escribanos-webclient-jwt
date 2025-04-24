$(document).ready(function () {
    $('[data-toggle="tooltip"]').tooltip();

    $('#form-cuit').on('submit', function (e) {
        const $cuit = $('#cuit');
        const cuit = $cuit.val().trim();

        const cuitRegex = /^(\d{11}|\d{2}-\d{8}-\d{1})$/;

        if (!cuit || !cuitRegex.test(cuit)) {
            e.preventDefault();

            $cuit
                .addClass('is-invalid')
                .attr('data-original-title', 'Ingresá un CUIT válido de 11 dígitos, con o sin guiones (xx-xxxxxxxx-x)')
                .tooltip('show');
        } else {
            $cuit
                .removeClass('is-invalid')
                .tooltip('hide');
        }
    });
});