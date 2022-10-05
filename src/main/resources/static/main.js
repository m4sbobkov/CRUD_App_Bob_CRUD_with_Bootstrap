

$('document').ready(function (){

    $('.table .eBtn, .dBtn').on('click', function (event){

        event.preventDefault();

        var href= $(this).attr('href');
        var text= $(this).text();

        if (text==='Edit'){
            $.get(href, function (user, status) {
                $('.editForm #edit-Id').val(user.id);
                $('.editForm #edit-name').val(user.name);
                $('.editForm #edit-age').val(user.age);
                $('.editForm #edit-email').val(user.email);
                $('.editForm #edit-username').val(user.username);
                $('.editForm #edit-role').val(user.roles);
            })
            $('.editForm #edit-user').modal('show');
        } else {
            $.get(href, function (user, status) {
                $('.deleteForm #delete-Id').val(user.id);
                $('.deleteForm #delete-name').val(user.name);
                $('.deleteForm #delete-age').val(user.age);
                $('.deleteForm #delete-email').val(user.email);
                $('.deleteForm #delete-username').val(user.username);
                $('.deleteForm #delete-role').val(user.roles);
            })
            $('.deleteForm #delete-user').modal('show');
        }


    });


});