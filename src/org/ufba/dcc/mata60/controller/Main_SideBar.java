package org.ufba.dcc.mata60.controller;

import org.ufba.dcc.mata60.services.SidebarPage;
import org.ufba.dcc.mata60.services.SidebarPageConfig;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.SerializableEventListener;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Image;
import org.zkoss.zul.Include;
import org.zkoss.zul.Label;
import org.zkoss.zul.Rows;

import com.sun.rowset.internal.Row;

public class Main_SideBar extends SelectorComposer<Component> {

	@Wire
	Grid fnList;

	// services
	SidebarPageConfig pageConfig = new Main_SideBarPageConfig();

	@Override
	public void doAfterCompose(Component comp) throws Exception {
		
		super.doAfterCompose(comp);
		// initialize view after view construction.
		Rows rows = fnList.getRows();
		
		for (SidebarPage page : pageConfig.getPages()) {
			
			org.zkoss.zul.Row row = constructSidebarRow(page.getName(), page.getLabel(), page.getIconUri(),
					page.getUri());
			
			rows.appendChild(row);
			
		}
	}

	private org.zkoss.zul.Row constructSidebarRow(final String name, String label, String imageSrc,
			final String locationUri) {

		// construct component and hierarchy
		org.zkoss.zul.Row row = new org.zkoss.zul.Row();
		Image image = new Image(imageSrc);
		Label lab = new Label(label);

		row.appendChild(image);
		row.appendChild(lab);

		// set style attribute
		row.setSclass("sidebar-fn");

		// create and register event listener
		EventListener<Event> actionListener = new SerializableEventListener<Event>() {
			
			private static final long serialVersionUID = 1L;

			public void onEvent(Event event) throws Exception {
				//redirect current url to new location
				if(locationUri.startsWith("http")){
					//open a new browser tab
					Executions.getCurrent().sendRedirect(locationUri);
				}else{
					//use iterable to find the first include only
					Include include = (Include)Selectors.iterable(fnList.getPage(), "#mainInclude")
							.iterator().next();
					include.setSrc(locationUri);
					
					//advance bookmark control, 
					//bookmark with a prefix
					if(name!=null){
						getPage().getDesktop().setBookmark("p_"+name);
					}
				}
			}
		};

		row.addEventListener(Events.ON_CLICK, actionListener);

		return row;
	}
}
