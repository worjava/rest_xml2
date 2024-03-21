package com.shop.soap_server.service.Impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import com.shop.soap_server.model.PurchaseInfo;
import com.shop.soap_server.model.dto.ProductInfoDto;
import com.shop.soap_server.model.dto.TopBuyerDto;
import com.shop.soap_server.repository.PurchaseInfoRepository;
import com.shop.soap_server.service.PurchaseInfoService;
import org.springframework.stereotype.Service;

@Service
public class PurchaseInfoServiceImpl implements PurchaseInfoService {

    private final PurchaseInfoRepository repository;

    public PurchaseInfoServiceImpl(PurchaseInfoRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<PurchaseInfo> findPurchasesLastWeek() {
        LocalDate endDate = LocalDate.now();
        LocalDate start = endDate.minusWeeks(1);

        return repository.findPurchasesLastWeek(start, endDate);
    }

    @Override
    public ProductInfoDto findMostPurchasedItemLastMonth() {
        List<Object[]> pars = repository.findMostPurchasedItemLastMonth(
                LocalDate.now().minusMonths(1).withDayOfMonth(1),
                LocalDate.now()
        );

        checkAndThrowIfEmpty(pars);

        Object[] result = pars.get(0);
        String name = (String) result[0];
        int salesCount = ((Number) result[1]).intValue();

        return new ProductInfoDto(name, salesCount);
    }

    @Override
    public ProductInfoDto getTopItemPurchased18YearsOlds() {
        List<Object[]> pars = repository.getTopItemPurchasedBy18YearOlds();

        checkAndThrowIfEmpty(pars);

        Object[] result = pars.get(0);
        String name = (String) result[0];
        int salesCount = ((Number) result[1]).intValue();
        System.out.println(name);
        System.out.println(salesCount);
        return new ProductInfoDto(name, salesCount);
    }

    @Override
    public TopBuyerDto findTopBuyerOverLastSixMonths() {
        List<Object[]> pars = repository.findTopBuyerOverLastSixMonths(LocalDate.now().minusMonths(6));

        checkAndThrowIfEmpty(pars);

        Object[] result = pars.get(0);
        String name = (String) result[0];
        String lastName = (String) result[1];
        int salesCount = ((Number) result[2]).intValue();

        return new TopBuyerDto(name, lastName, salesCount);
    }

    private void checkAndThrowIfEmpty(List<Object[]> result) {
        if (Objects.isNull(result) || result.isEmpty()) {
            throw new IllegalStateException("Результат запроса равен null или пуст");
        }
    }
}
