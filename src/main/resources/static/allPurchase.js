document.addEventListener('DOMContentLoaded', function() {
    fetch('http://localhost:8080/api/purchase/all')
        .then(response => response.text())
        .then(str => (new window.DOMParser()).parseFromString(str, "text/xml"))
        .then(data => {
            const purchases = data.querySelectorAll('purchaseInfo');
            const purchasesContainer = document.getElementById('purchasesContainer');

            if (purchases.length > 0) {
                purchases.forEach(purchase => {
                    const id = purchase.querySelector('id').textContent;
                    const firstName = purchase.querySelector('firstName').textContent;
                    const lastName = purchase.querySelector('lastName').textContent;
                    const age = purchase.querySelector('age').textContent;
                    const count = purchase.querySelector('count').textContent;
                    const purchaseItem = purchase.querySelector('purchaseItem').textContent;
                    const amount = purchase.querySelector('amount').textContent;
                    let purchaseDate = purchase.querySelector('purchaseDate').textContent;
                    purchaseDate = purchaseDate ? purchaseDate : 'Не указана';

                    const purchaseElement = document.createElement('div');
                    purchaseElement.innerHTML = `
                    <p>ID: ${id}</p>
                    <p>Имя: ${firstName}</p>
                    <p>Фамилия: ${lastName}</p>
                    <p>Возраст: ${age}</p>
                    <p>Количество: ${count}</p>
                    <p>Товар: ${purchaseItem}</p>
                    <p>Сумма: ${amount}</p>
                    <p>Дата покупки: ${purchaseDate}</p>
                `;
                    purchasesContainer.appendChild(purchaseElement);
                });
            } else {
                purchasesContainer.innerHTML = '<p>Покупки не найдены.</p>';
            }
        })
        .catch(error => {
            console.error('Ошибка при получении данных: ', error);
            purchasesContainer.innerHTML = '<p>Произошла ошибка при загрузке данных.</p>';
        });
});
