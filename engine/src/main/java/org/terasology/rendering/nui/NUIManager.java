/*
 * Copyright 2013 MovingBlocks
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.terasology.rendering.nui;

import org.terasology.asset.AssetUri;
import org.terasology.classMetadata.ClassLibrary;
import org.terasology.entitySystem.systems.ComponentSystem;

/**
 * @author Immortius
 */
public interface NUIManager extends ComponentSystem {

    UIScreen pushScreen(AssetUri screenUri);

    UIScreen pushScreen(String screenUri);

    <T extends UIScreen> T pushScreen(AssetUri screenUri, Class<T> expectedType);

    <T extends UIScreen> T  pushScreen(String screenUri, Class<T> expectedType);

    void pushScreen(UIScreen screen);

    void popScreen();

    UIScreen setScreen(AssetUri screenUri);

    UIScreen setScreen(String screenUri);

    <T extends UIScreen> T setScreen(AssetUri screenUri, Class<T> expectedType);

    <T extends UIScreen> T setScreen(String screenUri, Class<T> expectedType);

    void setScreen(UIScreen screen);

    void closeScreens();

    void render();

    void update(float delta);

    ClassLibrary<UIWidget> getElementMetadataLibrary();

    void setFocus(UIWidget element);

    UIWidget getFocus();
}
