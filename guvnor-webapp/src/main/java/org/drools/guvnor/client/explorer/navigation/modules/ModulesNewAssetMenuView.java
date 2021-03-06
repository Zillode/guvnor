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

import com.google.gwt.user.client.ui.IsWidget;
import org.drools.guvnor.client.explorer.ClientFactory;

public interface ModulesNewAssetMenuView extends IsWidget {

    interface Presenter {

        void onNewModule();

        void onNewSpringContext();

        void onNewWorkingSet();

        void onNewRule();

        void onNewRuleTemplate();

        void onNewPojoModel();

        void onNewDeclarativeModel();

        void onNewBPELPackage();

        void onNewFunction();

        void onNewDSL();

        void onNewRuleFlow();

        void onNewBPMN2Process();

        void onNewWorkitemDefinition();

        void onNewEnumeration();

        void onNewTestScenario();

        void onNewFile();

        void onRebuildAllPackages();

        void onRebuildConfirmed();

    }

    void setPresenter( Presenter presenter );

    void openNewPackageWizard(ClientFactory clientFactory);

    void openNewAssetWizardWithoutCategories( String format );

    void openNewAssetWizardWithCategories( String format );

    void confirmRebuild();

    void showLoadingPopUpRebuildingPackageBinaries();

    void closeLoadingPopUp();
}
