$(function (){
    $('#languageSelector').change(function (){
        const lang = $('#languageSelector').val();
        if(lang) {
            window.location.replace('?lang=' + lang);
        }
    });
});