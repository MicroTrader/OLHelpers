/*
 * _______________________________________________________________________________
 *
 * Copyright (c) 2016. Suminda Sirinath Salpitikorala Dharmasena and
 *     OLHelpers Project Contributors
 *
 * OLHelpers, is a collection of works and / or artifacts submitted
 * and / or contributed by multiple authors ("Project Contributors"),
 * collectively creating a larger work(s) and / or artifact(s) (the / this
 * "Project"). This project is licensed under the terms of either:
 *  - the Apache License, Version 2.0 (ASL 2.0), or
 *  - the Academic Free License, Version 3.0 (AFL 3.0), or
 *  - the MIT License (MIT), collectively referred as the "Project Licenses".
 * As a recipient of this Project, you may choose which license to receive
 * the Project under (except as noted in other explicit declarations and / or
 * notices of intellectual property rights). Some artifacts and / or works may
 * not be the intellectual property of the Project Contributors. These are
 * noted in explicit declarations and / or notices of intellectual property
 * rights.
 *
 * This Project uses a shared copyright model. Each contributor maintains
 * copyright over their contributions and / or submissions to this Project.
 * Contributions and / or submissions are typically transformation,
 * modification or adaptation of existing artifacts or works (underlying work)
 * resulting in derivative works. Thus, the Project artifacts, in its entirety
 * is not the copyright of any single person or institution. Instead, it is the
 * collective copyright of all those who have made contributions and / or
 * submissions to the Project.
 *
 * No contributions and / or submissions are allowed under licenses which are
 * fundamentally incompatible with the Project Licenses under which this
 * Project is licenced under. By contributing or making a submission to this
 * Project you hereby understand and agree to the following:
 *  - your contributions and / or submissions are licensed under the terms of
 *    all the Project Licenses whereas the recipients are free to choose under
 *    which license the contributions and submission is received under;
 *  - you will only make contributions and / or submissions which you own the
 *    intellectual property right or have appropriate and adequate licenses,
 *    and in addition have the authority and ability to make the contributions
 *    and / or submissions, under the terms of the Project Licenses, without
 *    encumbrances, limitations and restrictions whatsoever;
 *  - you comply and adhere to the adopted: code of conduct, norms, etiquettes
 *    and protocols of the Project in all you dealing with the Project.
 *
 * _______________________________________________________________________________
 *
 * Copyright (c) 2016. Suminda Sirinath Salpitikorala Dharmasena and
 *     OLHelpers Project Contributors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at:
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * _______________________________________________________________________________
 *
 * Copyright (c) 2016. Suminda Sirinath Salpitikorala Dharmasena and
 *     OLHelpers Project Contributors
 *
 * Licensed under the Academic Free License, Version 3.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * https://opensource.org/licenses/AFL-3.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * _______________________________________________________________________________
 *
 * The MIT License (MIT)
 * Copyright (c) 2016. Suminda Sirinath Salpitikorala Dharmasena and
 *     OLHelpers Project Contributors
 *
 * Permission is hereby granted, free of charge, to any person obtaining a
 * copy of this software and associated documentation files (the "Software"),
 * to deal in the Software without restriction, including without limitation
 * the rights to use, copy, modify, merge, publish, distribute, sublicense,
 * and/or sell copies of the Software, and to permit persons to whom the
 * Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included
 * in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL
 * THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE,
 * ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER
 * DEALINGS IN THE SOFTWARE.
 *
 * _______________________________________________________________________________
 */


package com.sakrio.utils.box.mutable;


import com.sakrio.utils.UnsafeAccess;
import com.sakrio.utils.box.BoxOnce;
import com.sakrio.utils.box.immutable.ImmutableEnum;
import sun.misc.Unsafe;

/**
 * Wrapper class
 *
 * @author sirinath
 */
@SuppressWarnings("serial")
public final class MutableEnum<T extends Enum<T>> extends Number
        implements BoxOnce<MutableEnum<T>> {
    protected final static long valueFieldOffset = UnsafeAccess.getFieldOffset(MutableEnum.class, "value");
    private static final Unsafe UNSAFE = UnsafeAccess.UNSAFE;
    /**
     * Value
     */
    private Enum value;

    /**
     * @param i Parameter
     */
    public MutableEnum(final Enum i) {
        value = i;
    }

    @Override
    public final String toString() {
        return String.valueOf(value);
    }

    public final Enum getValue() {
        return value;
    }

    public final void setValue(final Enum value) {
        this.value = value;
    }

    public final Enum get() {
        return value;
    }

    public final Enum getValueVolatile() {
        return (Enum) UNSAFE.getObjectVolatile(this, valueFieldOffset);
    }

    public final void setValueVolatile(final Enum value) {
        UNSAFE.putObjectVolatile(this, valueFieldOffset, value);
    }

    public final void set(final Enum value) {
        this.value = value;
    }

    @Override
    public final boolean equals(Object other) {
        if (other instanceof MutableEnum)
            return value == ((MutableEnum<T>) other).getValue();
        else if (other instanceof ImmutableEnum)
            return value == ((ImmutableEnum) other).getValue();
        else if (other instanceof Enum)
            return other.equals(value);
        else
            return false;
    }

    @Override
    public final int compareTo(final MutableEnum<T> other) {
        return value.compareTo(other.getValue());
    }

    public final int compareTo(final ImmutableEnum<T> other) {
        return value.compareTo(other.getValue());
    }

    // Enum

    @Override
    public final int intValue() {
        return value.ordinal();
    }

    @Override
    public final long longValue() {
        return (long) value.ordinal();
    }

    @Override
    public final float floatValue() {
        return (float) value.ordinal();
    }

    @Override
    public final double doubleValue() {
        return (double) value.ordinal();
    }

    @Override
    public final int hashCode() {
        return value.hashCode();
    }
}