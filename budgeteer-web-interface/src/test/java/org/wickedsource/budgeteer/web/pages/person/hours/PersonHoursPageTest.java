package org.wickedsource.budgeteer.web.pages.person.hours;

import org.apache.wicket.util.tester.WicketTester;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.wickedsource.budgeteer.MoneyUtil;
import org.wickedsource.budgeteer.service.person.PersonDetailData;
import org.wickedsource.budgeteer.service.person.PersonService;
import org.wickedsource.budgeteer.web.AbstractWebTestTemplate;

import java.util.Date;

import static org.mockito.Mockito.when;

public class PersonHoursPageTest extends AbstractWebTestTemplate {

    @Autowired
    private PersonService service;

    @Test
    void render() {
        WicketTester tester = getTester();
        when(service.loadPersonDetailData(1L)).thenReturn(createPerson());
        tester.startPage(PersonHoursPage.class, PersonHoursPage.createParameters(1L));
        tester.assertRenderedPage(PersonHoursPage.class);
    }

    private PersonDetailData createPerson() {
        PersonDetailData data = new PersonDetailData();
        data.setAverageDailyRate(MoneyUtil.createMoney(100.0));
        data.setName("Tom Hombergs");
        data.setBudgetBurned(MoneyUtil.createMoney(100000.00));
        data.setFirstBookedDate(new Date());
        data.setHoursBooked(100.0);
        data.setLastBookedDate(new Date());
        return data;
    }

    @Override
    protected void setupTest() {

    }
}
