package org.wickedsource.budgeteer.web.components.breadcrumb;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.wickedsource.budgeteer.web.dashboard.DashboardPage;

import java.util.List;

public class BreadcrumbsPanel extends Panel {

    public BreadcrumbsPanel(String wicketId, IModel<List<Breadcrumb>> model) {
        super(wicketId, model);
        setRenderBodyOnly(true);
        add(createBreadcrumbsList(model));
    }

    private ListView<Breadcrumb> createBreadcrumbsList(final IModel<List<Breadcrumb>> model) {
        ListView<Breadcrumb> breadcrumbsList = new ListView<Breadcrumb>("breadcrumbsList", model) {
            @Override
            protected void populateItem(ListItem<Breadcrumb> item) {
                Link link = new BookmarkablePageLink("breadcrumbLink", item.getModelObject().getTargetPage());
                Label label = new Label("breadcrumbTitle", item.getModelObject().getTitle());
                label.setRenderBodyOnly(true);
                link.add(label);
                item.add(link);
            }
        };
        return breadcrumbsList;
    }

}