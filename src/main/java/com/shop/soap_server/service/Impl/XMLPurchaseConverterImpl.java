package com.shop.soap_server.service.Impl;

import com.shop.soap_server.exception.ValidationException;
import com.shop.soap_server.mapper.PurchaseInfoMapper;
import com.shop.soap_server.model.PurchaseInfo;
import com.shop.soap_server.model.Purchases;
import com.shop.soap_server.model.dto.PurchaseInfoDto;
import com.shop.soap_server.service.XMLPurchaseConverter;
import com.shop.soap_server.service.XMLValidatorService;
import com.shop.soap_server.util.LocalDateAdapter;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.ParserConfigurationException;
import java.io.StringReader;
import java.io.StringWriter;

@Service
public class XMLPurchaseConverterImpl implements XMLPurchaseConverter {
    public XMLPurchaseConverterImpl(XMLValidatorService xmlValidatorService) {
        this.xmlValidatorService = xmlValidatorService;
    }

    private final XMLValidatorService xmlValidatorService;


    @Override
    public PurchaseInfo convertToPurchaseInfo(String purchaseXml) {
        try {
            if (!xmlValidatorService.isValid(purchaseXml)) {
                throw new ValidationException("Покупка не обновлена. Ошибка Валидации");
            }

            PurchaseInfoDto dto = parseXmlToDto(purchaseXml);
            return PurchaseInfoMapper.INSTANCE.dtoToEntity(dto);
        } catch (JAXBException e) {
            e.printStackTrace();
            throw new ValidationException("Ошибка XML: " + e.getMessage());
        } catch (ParserConfigurationException e) {
            throw new RuntimeException("Ошибка конфигурации парсера XML", e);
        }
    }

    @Override
    public String convertToXML(Purchases info) {
        try {
            JAXBContext context = JAXBContext.newInstance(Purchases.class, PurchaseInfo.class, LocalDateAdapter.class,PurchaseInfoDto.class);

            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            StringWriter writer = new StringWriter();
            marshaller.marshal(info, writer);

            return writer.toString();
        } catch (JAXBException e) {
            e.printStackTrace();
            return null;
        }
    }

    private  PurchaseInfoDto parseXmlToDto(String xmlData) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(PurchaseInfoDto.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        StringReader reader = new StringReader(xmlData);
        return (PurchaseInfoDto) unmarshaller.unmarshal(reader);
    }


}
