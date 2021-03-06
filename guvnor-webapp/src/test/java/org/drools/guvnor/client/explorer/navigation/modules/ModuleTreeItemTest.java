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

import com.google.gwt.user.client.ui.IsTreeItem;
import org.drools.guvnor.client.common.AssetEditorFactory;
import org.drools.guvnor.client.explorer.ClientFactory;
import org.drools.guvnor.client.explorer.ModuleEditorPlace;
import org.drools.guvnor.client.explorer.navigation.NavigationViewFactory;
import org.drools.guvnor.client.rpc.PackageConfigData;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class ModuleTreeItemTest {


    private IsTreeItem treeItem;
    private ModuleTreeItemView view;

    @Test
    public void testSetRootItem() throws Exception {
        treeItem = mock( IsTreeItem.class );
        view = mock( ModuleTreeItemView.class );
        ClientFactory clientFactory = mock( ClientFactory.class );

        AssetEditorFactory assetEditorFactory = mock( AssetEditorFactory.class );
        when(
                clientFactory.getAssetEditorFactory()
        ).thenReturn(
                assetEditorFactory
        );

        when(
                assetEditorFactory.getRegisteredAssetEditorFormats()
        ).thenReturn(
                new String[0]
        );

        NavigationViewFactory navigationViewFactory = mock( NavigationViewFactory.class );
        when(
                clientFactory.getNavigationViewFactory()
        ).thenReturn(
                navigationViewFactory
        );
        when(
                navigationViewFactory.getModuleTreeItemView()
        ).thenReturn(
                view
        );

        PackageConfigData packageConfigData = mock( PackageConfigData.class );
        when(
                packageConfigData.getUuid()
        ).thenReturn(
                "mockUuid"
        );
        new ModuleTreeItem( clientFactory, treeItem, packageConfigData );

        verify( view ).setRootItem( treeItem );

        ArgumentCaptor<ModuleEditorPlace> moduleEditorPlaceArgumentCaptor = ArgumentCaptor.forClass( ModuleEditorPlace.class );
        verify( view ).setRootUserObject( moduleEditorPlaceArgumentCaptor.capture() );
        ModuleEditorPlace moduleEditorPlace = moduleEditorPlaceArgumentCaptor.getValue();

        assertEquals( "mockUuid", moduleEditorPlace.getUuid() );
    }


}
