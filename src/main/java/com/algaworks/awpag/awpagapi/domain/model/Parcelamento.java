package com.algaworks.awpag.awpagapi.domain.model;

import com.algaworks.awpag.awpagapi.domain.validation.ValidationGroups;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import jakarta.validation.groups.ConvertGroup;
import jakarta.validation.groups.Default;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
public class Parcelamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Valid
    @ConvertGroup(from = Default.class, to = ValidationGroups.ClienteId.class)
    @NotNull
    @ManyToOne
    private Cliente cliente;

    @NotBlank
    @Size(max=20)
    private String descricao;

    @NotNull
    @Positive
    private BigDecimal ValorTotal;

    @NotNull
    @Positive
    @Max(value=12)
    private Integer quatidade;

    private OffsetDateTime dateTime;
}
