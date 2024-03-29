package com.shop.soap_server.init;

import com.shop.soap_server.model.PurchaseInfo;
import com.shop.soap_server.model.PurchaseItem;
import com.shop.soap_server.model.User;
import com.shop.soap_server.repository.PurchaseInfoRepository;
import com.shop.soap_server.repository.UserRepository;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class InitData {


    private final UserRepository userRepository;

    private final PurchaseInfoRepository purchaseInfoRepository;

    public InitData(UserRepository userRepository, PurchaseInfoRepository purchaseInfoRepository) {
        this.userRepository = userRepository;
        this.purchaseInfoRepository = purchaseInfoRepository;
    }

    @PostConstruct
    public void init() {
        List<User> users = generateUsers();
        List<PurchaseInfo> purchases = generatePurchases(users);

        userRepository.saveAll(users);
        purchaseInfoRepository.saveAll(purchases);
    }

    private List<User> generateUsers() {
        List<User> users = new ArrayList<>();

        users.add(new User("Иван", "Иванов", 18));
        users.add(new User("Петр", "Петров", 25));
        users.add(new User("Николай", "Николаев", 30));
        users.add(new User("Алексей", "Алексеев", 22));
        users.add(new User("Дмитрий", "Дмитриев", 35));
        users.add(new User("Сергей", "Сергеев", 27));
        users.add(new User("Михаил", "Михайлов", 19));
        users.add(new User("Андрей", "Андреев", 28));
        users.add(new User("Александр", "Александров", 31));
        users.add(new User("Егор", "Егоров", 20));
        users.add(new User("Игорь", "Игорев", 29));
        users.add(new User("Владимир", "Владимиров", 24));
        users.add(new User("Кирилл", "Кириллов", 32));
        users.add(new User("Олег", "Олегов", 26));
        users.add(new User("Григорий", "Григорьев", 33));

        return users;
    }

    private List<PurchaseInfo> generatePurchases(List<User> users) {
        List<PurchaseInfo> purchases = new ArrayList<>();

        for (User user : users) {
            // Добавляем покупки для каждого пользователя
            if (user.getAge() == 18) {
                purchases.add(new PurchaseInfo(user, 2, PurchaseItem.SMARTPHONE, new BigDecimal("50"), LocalDate.parse("2024-03-28")));
                purchases.add(new PurchaseInfo(user, 1, PurchaseItem.TELEVISION, new BigDecimal("100"), LocalDate.parse("2024-03-29")));
            } else if (user.getAge() == 25) {
                purchases.add(new PurchaseInfo(user, 1, PurchaseItem.JUICER, new BigDecimal("80"), LocalDate.parse("2024-03-27")));
                purchases.add(new PurchaseInfo(user, 3, PurchaseItem.HEADPHONES, new BigDecimal("120"), LocalDate.parse("2024-03-28")));
            } else if (user.getAge() == 30) {
                purchases.add(new PurchaseInfo(user, 1, PurchaseItem.KEYBOARD, new BigDecimal("30"), LocalDate.parse("2024-03-27")));
                purchases.add(new PurchaseInfo(user, 2, PurchaseItem.SMARTPHONE, new BigDecimal("150"), LocalDate.parse("2024-03-28")));
            }
            purchases.add(new PurchaseInfo(user, 1, PurchaseItem.KEYBOARD, new BigDecimal("100"), LocalDate.parse("2024-03-30")));
            purchases.add(new PurchaseInfo(user, 1, PurchaseItem.TELEVISION, new BigDecimal("200"), LocalDate.parse("2024-03-31")));
        }

        return purchases;
    }

}
