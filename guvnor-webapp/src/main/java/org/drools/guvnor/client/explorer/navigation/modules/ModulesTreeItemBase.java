/*
 * Copyright 2011 JBoss Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.drools.guvnor.client.explorer.navigation.modules;

import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.IsTreeItem;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import org.drools.guvnor.client.explorer.ClientFactory;
import org.drools.guvnor.client.explorer.navigation.modules.ModulesTreeItemBaseView.Presenter;
import org.drools.guvnor.client.rpc.PackageConfigData;

public abstract class ModulesTreeItemBase implements IsWidget, Presenter {

    protected ModulesTreeItemBaseView view;
    protected ClientFactory clientFactory;

    public ModulesTreeItemBase( ClientFactory clientFactory, ModulesTreeItemBaseView view ) {
        this.view = view;
        view.setPresenter( this );
        this.clientFactory = clientFactory;
        setUpRootItem();
    }

    protected void setUpRootItem() {
        fillModulesTree( view.addModulesTreeItem() );
    }

    protected abstract void fillModulesTree( final IsTreeItem treeItem );

    public void onModuleSelected( Object userObject ) {
        if ( userObject instanceof Place ) {
            clientFactory.getPlaceController().goTo( (Place) userObject );
        }
    }

    protected void addModules( PackageConfigData[] packageConfigDatas, IsTreeItem treeItem ) {
        for (PackageConfigData packageConfigData : packageConfigDatas) {
            addSubPackages(
                    createModuleTreeItem( treeItem, packageConfigData ),
                    packageConfigData.getSubPackages() );
        }
    }

    protected void addSubPackages( ModuleTreeItem moduleTreeItem, PackageConfigData[] subPackages ) {
        if ( subPackages != null ) {
            addModules(
                    subPackages,
                    moduleTreeItem.getRootItem() );
        }
    }

    protected ModuleTreeItem createModuleTreeItem( IsTreeItem treeItem, PackageConfigData packageConfigData ) {
        return new ModuleTreeItem(
                clientFactory,
                view.addModuleTreeItem( treeItem, packageConfigData.getName() ),
                packageConfigData
        );
    }

    public Widget asWidget() {
        return view.asWidget();
    }
}
