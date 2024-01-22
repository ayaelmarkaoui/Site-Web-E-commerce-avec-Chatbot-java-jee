$(document).ready(function () {
    $('table').on('click', '.editButton', function (event) {
        event.preventDefault();

        // Utilisation de $(this) pour accéder au bouton cliqué
        var href = $(this).attr('href');

        // Utilisation de la méthode ajax pour une gestion plus détaillée des requêtes
        $.ajax({
            type: 'GET',
            url: href,
            success: function (category, status) {
                // Assurez-vous que la requête s'est déroulée avec succès
                if (status === 'success') {
                    // Remplir les champs du formulaire avec les données de la catégorie
                    $('#idEdit').val(category.id);
                    $('#nameEdit').val(category.name);

                    // Afficher le modal
                    $('#editModal').modal();
                } else {
                    console.error('Erreur lors de la récupération des données de la catégorie.');
                }
            },
            error: function () {
                console.error('Erreur lors de la requête GET.');
            }
        });
    });
});
// $('document').ready(function (){
//     $('table #editButton').on('click', function (event){
//         event.preventDefault();
//         var href = $(this).attr('href');
//         $.get(href, function (category, status){
//             $('#idEdit').val(category.id);
//             $('#nameEdit').val(category.name);
//         });
//         $('#editModal').modal();
//     });
// });
//Ce code permet de gérer le clic sur un bouton d'édition à l'intérieur d'un tableau.
// Lorsque le bouton est cliqué, il envoie une requête GET au serveur pour récupérer des
// informations sur une catégorie qui a un  puis remplit
// un formulaire dans une boîte modale avec ces informations et affiche la boîte modale