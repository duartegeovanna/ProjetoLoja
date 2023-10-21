package com.example.projetoLoja.mapper;

import com.example.projetoLoja.dto.ProductRecordDto;
import com.example.projetoLoja.model.ProductModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductModel create(ProductRecordDto productRecordDto);

    ProductModel update(ProductRecordDto productRecordDto);

}
