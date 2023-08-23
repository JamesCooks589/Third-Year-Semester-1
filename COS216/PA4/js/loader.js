//James Cooks u21654680
window.addEventListener("load", function() {
    const loader = document.querySelector(".loader");

    loader.classList.add("loader-hidden");

    loader.addEventListener("transitionend", function() {
        //remove loader from the DOM
        loader.remove();
    });
});