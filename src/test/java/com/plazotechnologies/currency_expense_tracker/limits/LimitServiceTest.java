package com.plazotechnologies.currency_expense_tracker.limits;

import com.plazotechnologies.currency_expense_tracker.limits.core.LimitDto;
import com.plazotechnologies.currency_expense_tracker.limits.dao.ILimitMapper;
import com.plazotechnologies.currency_expense_tracker.limits.dao.ILimitRepository;
import com.plazotechnologies.currency_expense_tracker.limits.dao.model.Limit;
import com.plazotechnologies.currency_expense_tracker.limits.service.LimitService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Spy;

import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class LimitServiceTest {

    @Spy
    private ILimitRepository limitRepository;

    @Spy
    private ILimitMapper limitMapper;

    @InjectMocks
    private LimitService limitService;

    @Test
    @DisplayName("createLimitTest")
    public void createLimitTest() {
        LocalDate limitSettingDate;
        LimitDto limitDto = new LimitDto(UUID.randomUUID(), limitSettingDate = LocalDate.now(),
                new BigDecimal( 2000), limitSettingDate.withDayOfMonth(limitSettingDate.lengthOfMonth()), "USD");

        when(limitMapper.toEntity(limitDto))
                .thenReturn(new Limit(limitDto.getLimitSettingDate(), limitDto.getLimitValue(),
                        limitDto.getLimitCurrencyShortname()));

        limitService.createLimit(limitDto);

        verify(limitMapper, times(1)).toEntity(limitDto);
        verify(limitRepository, times(1)).save(any(Limit.class));
    }

    @Test
    @DisplayName("getLimitByDateTest")
    public void getLimitByDateTest(){
        Limit limit = new Limit(LocalDate.now(), new BigDecimal(2000), "USD");
        when(limitRepository.getByLimitSettingDate(limit.getLimitSettingDate())).thenReturn(limit);

        when(limitMapper.toDTO(limit)).thenReturn(new LimitDto(
                UUID.randomUUID(), limit.getLimitSettingDate(), limit.getLimitValue(),
                limit.getLimitSettingDate(), limit.getLimitCurrencyShortname()
        ));

        LimitDto result = limitService.getLimitByDate(limit.getLimitSettingDate());

        verify(limitRepository, times(1)).getByLimitSettingDate(limit.getLimitSettingDate());
        verify(limitMapper, times(1)).toDTO(limit);

        assertEquals(limit.getLimitSettingDate(), result.getLimitSettingDate());
        assertEquals(limit.getLimitValue(), result.getLimitValue());
        assertEquals(limit.getLimitSettingDate(), result.getLastDayOfMonth());
        assertEquals(limit.getLimitCurrencyShortname(), result.getLimitCurrencyShortname());
    }
}