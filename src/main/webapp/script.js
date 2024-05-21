function toggleFavorite(comicId) {
    fetch(`/IT2024-Lab4/FC?page=favourite&comicId=${comicId}`, {
        method: 'POST',
    })
        .then(response => {
            console.log(response);
            if (!response.ok)
                throw new Error('Failed to add to favorites');
            console.log('Added to favorites: ' + comicId);
        })
        .catch(error => {
            console.error('Error adding to favorites:', error);
        });
}