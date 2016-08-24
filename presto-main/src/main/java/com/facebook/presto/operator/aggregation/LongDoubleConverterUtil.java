/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.facebook.presto.operator.aggregation;

final class LongDoubleConverterUtil
{
    private LongDoubleConverterUtil() {}

    /**
     * Converts a double value to a sortable long. The value is converted by getting their IEEE 754
     * floating-point bit layout. Some bits are swapped to be able to compare the result as long.
     */
    public static double sortableLongToDouble(long value)
    {
        value = value ^ (value >> 63) & Long.MAX_VALUE;
        return Double.longBitsToDouble(value);
    }

    /**
     * Converts a sortable long to double.
     *
     * @see #sortableLongToDouble(long)
     */
    public static long doubleToSortableLong(double value)
    {
        long bits = Double.doubleToRawLongBits(value);
        return bits ^ (bits >> 63) & Long.MAX_VALUE;
    }
}