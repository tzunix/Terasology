/*
 * Copyright 2019 MovingBlocks
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
package org.terasology.rendering.nui.widgets.types.math;

import org.terasology.math.geom.Rect2f;
import org.terasology.math.geom.Vector4f;
import org.terasology.reflection.TypeInfo;
import org.terasology.rendering.nui.UIWidget;
import org.terasology.rendering.nui.databinding.Binding;
import org.terasology.rendering.nui.widgets.types.RegisterTypeWidgetFactory;
import org.terasology.rendering.nui.widgets.types.TypeWidgetFactory;
import org.terasology.rendering.nui.widgets.types.TypeWidgetLibrary;

import java.util.Optional;

@RegisterTypeWidgetFactory
public class Rect2fWidgetFactory implements TypeWidgetFactory {
    @Override
    public <T> Optional<UIWidget> create(Binding<T> binding, TypeInfo<T> type, TypeWidgetLibrary library) {
        if (!Rect2f.class.equals(type.getRawType())) {
            return Optional.empty();
        }

        Binding<Rect2f> rectBinding = (Binding<Rect2f>) binding;

        if (rectBinding.get() == null || rectBinding.get().isEmpty()) {
            // Make non-empty so that editing works as intended
            // When the initial rect is empty, editing any of the components will make no difference
            // since one of the size components will always be zero, making the factory methods always
            // return the empty rect
            rectBinding.set(Rect2f.createFromMinAndSize(0, 0, 1, 1));
        }

        LabeledNumberRowLayoutBuilder<Float> builder =
            new LabeledNumberRowLayoutBuilder<>(float.class, library)
                .add("x", new Binding<Float>() {
                    @Override
                    public Float get() {
                        return rectBinding.get().minX();
                    }

                    @Override
                    public void set(Float value) {
                        Rect2f old = rectBinding.get();
                        rectBinding.set(Rect2f.createFromMinAndSize(value, old.minY(), old.width(), old.height()));
                    }
                })
                .add("y", new Binding<Float>() {
                    @Override
                    public Float get() {
                        return rectBinding.get().minY();
                    }

                    @Override
                    public void set(Float value) {
                        Rect2f old = rectBinding.get();
                        rectBinding.set(Rect2f.createFromMinAndSize(old.minX(), value, old.width(), old.height()));
                    }
                })
                .add("w", new Binding<Float>() {
                    @Override
                    public Float get() {
                        return rectBinding.get().width();
                    }

                    @Override
                    public void set(Float value) {
                        Rect2f old = rectBinding.get();
                        rectBinding.set(Rect2f.createFromMinAndSize(old.minX(), old.minY(), value, old.height()));
                    }
                })
                .add("h", new Binding<Float>() {
                    @Override
                    public Float get() {
                        return rectBinding.get().height();
                    }

                    @Override
                    public void set(Float value) {
                        Rect2f old = rectBinding.get();
                        rectBinding.set(Rect2f.createFromMinAndSize(old.minX(), old.minY(), old.width(), value));
                    }
                });

        return Optional.of(builder.build());
    }
}
