/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.hugonardo.java.commons.collections;

import java.lang.reflect.Array;
import javax.annotation.Nonnull;

/**
 * <p>Operations on arrays, primitive arrays (like {@code int[]}) and
 * primitive wrapper arrays (like {@code Integer[]}).
 *
 * <p>This class tries to handle {@code null} input gracefully.
 * An exception will not be thrown for a {@code null}
 * array input. However, an Object array that contains a {@code null}
 * element may throw an exception. Each method documents its behaviour.
 *
 * <p>#ThreadSafe#
 *
 * @since 2.0
 */
public class ArrayUtils {

    /**
     * An empty immutable {@code String} array.
     */
    public static final String[] EMPTY_STRING_ARRAY = new String[0];

    /**
     * An empty immutable {@code int} array.
     */
    public static final int[] EMPTY_INT_ARRAY = new int[0];

    /**
     * <p>Copies the given array and adds the given element at the end of the new array.
     *
     * <p>The new array contains the same elements of the input
     * array plus the given element in the last position. The component type of
     * the new array is the same as that of the input array.
     *
     * <p>If the input array is {@code null}, a new one element array is returned
     * whose component type is the same as the element, unless the element itself is null,
     * in which case the return type is Object[]
     *
     * <pre>
     * ArrayUtils.add(null, null)      = IllegalArgumentException
     * ArrayUtils.add(null, "a")       = ["a"]
     * ArrayUtils.add(["a"], null)     = ["a", null]
     * ArrayUtils.add(["a"], "b")      = ["a", "b"]
     * ArrayUtils.add(["a", "b"], "c") = ["a", "b", "c"]
     * </pre>
     *
     * @param <T>     the component type of the array
     * @param array   the array to "add" the element to, may be {@code null}
     * @param element the object to add, may be {@code null}
     * @return A new array containing the existing elements plus the new element
     * The returned array type will be that of the input array (unless null),
     * in which case it will have the same type as the element.
     * If both are null, an IllegalArgumentException is thrown
     * @throws IllegalArgumentException if both arguments are null
     * @since 2.1
     */
    public static <T> T[] add(final T[] array, final T element) {
        Class<?> type;
        if (array != null) {
            type = array.getClass().getComponentType();
        } else if (element != null) {
            type = element.getClass();
        } else {
            throw new IllegalArgumentException("Arguments cannot both be null");
        }
        @SuppressWarnings("unchecked") // type must be T
        final T[] newArray = (T[]) copyArrayGrow1(array, type);
        newArray[newArray.length - 1] = element;
        return newArray;
    }

    /**
     * <p>Copies the given array and adds the given element at the end of the new array.
     *
     * <p>The new array contains the same elements of the input
     * array plus the given element in the last position. The component type of
     * the new array is the same as that of the input array.
     *
     * <p>If the input array is {@code null}, a new one element array is returned
     * whose component type is the same as the element.
     *
     * <pre>
     * ArrayUtils.add(null, true)          = [true]
     * ArrayUtils.add([true], false)       = [true, false]
     * ArrayUtils.add([true, false], true) = [true, false, true]
     * </pre>
     *
     * @param array   the array to copy and add the element to, may be {@code null}
     * @param element the object to add at the last index of the new array
     * @return A new array containing the existing elements plus the new element
     * @since 2.1
     */
    public static boolean[] add(final boolean[] array, final boolean element) {
        final boolean[] newArray = (boolean[]) copyArrayGrow1(array, Boolean.TYPE);
        newArray[newArray.length - 1] = element;
        return newArray;
    }

    /**
     * <p>Copies the given array and adds the given element at the end of the new array.
     *
     * <p>The new array contains the same elements of the input
     * array plus the given element in the last position. The component type of
     * the new array is the same as that of the input array.
     *
     * <p>If the input array is {@code null}, a new one element array is returned
     * whose component type is the same as the element.
     *
     * <pre>
     * ArrayUtils.add(null, 0)   = [0]
     * ArrayUtils.add([1], 0)    = [1, 0]
     * ArrayUtils.add([1, 0], 1) = [1, 0, 1]
     * </pre>
     *
     * @param array   the array to copy and add the element to, may be {@code null}
     * @param element the object to add at the last index of the new array
     * @return A new array containing the existing elements plus the new element
     * @since 2.1
     */
    public static byte[] add(final byte[] array, final byte element) {
        final byte[] newArray = (byte[]) copyArrayGrow1(array, Byte.TYPE);
        newArray[newArray.length - 1] = element;
        return newArray;
    }

    /**
     * <p>Copies the given array and adds the given element at the end of the new array.
     *
     * <p>The new array contains the same elements of the input
     * array plus the given element in the last position. The component type of
     * the new array is the same as that of the input array.
     *
     * <p>If the input array is {@code null}, a new one element array is returned
     * whose component type is the same as the element.
     *
     * <pre>
     * ArrayUtils.add(null, '0')       = ['0']
     * ArrayUtils.add(['1'], '0')      = ['1', '0']
     * ArrayUtils.add(['1', '0'], '1') = ['1', '0', '1']
     * </pre>
     *
     * @param array   the array to copy and add the element to, may be {@code null}
     * @param element the object to add at the last index of the new array
     * @return A new array containing the existing elements plus the new element
     * @since 2.1
     */
    public static char[] add(final char[] array, final char element) {
        final char[] newArray = (char[]) copyArrayGrow1(array, Character.TYPE);
        newArray[newArray.length - 1] = element;
        return newArray;
    }

    /**
     * <p>Copies the given array and adds the given element at the end of the new array.
     *
     * <p>The new array contains the same elements of the input
     * array plus the given element in the last position. The component type of
     * the new array is the same as that of the input array.
     *
     * <p>If the input array is {@code null}, a new one element array is returned
     * whose component type is the same as the element.
     *
     * <pre>
     * ArrayUtils.add(null, 0)   = [0]
     * ArrayUtils.add([1], 0)    = [1, 0]
     * ArrayUtils.add([1, 0], 1) = [1, 0, 1]
     * </pre>
     *
     * @param array   the array to copy and add the element to, may be {@code null}
     * @param element the object to add at the last index of the new array
     * @return A new array containing the existing elements plus the new element
     * @since 2.1
     */
    public static double[] add(final double[] array, final double element) {
        final double[] newArray = (double[]) copyArrayGrow1(array, Double.TYPE);
        newArray[newArray.length - 1] = element;
        return newArray;
    }

    /**
     * <p>Copies the given array and adds the given element at the end of the new array.
     *
     * <p>The new array contains the same elements of the input
     * array plus the given element in the last position. The component type of
     * the new array is the same as that of the input array.
     *
     * <p>If the input array is {@code null}, a new one element array is returned
     * whose component type is the same as the element.
     *
     * <pre>
     * ArrayUtils.add(null, 0)   = [0]
     * ArrayUtils.add([1], 0)    = [1, 0]
     * ArrayUtils.add([1, 0], 1) = [1, 0, 1]
     * </pre>
     *
     * @param array   the array to copy and add the element to, may be {@code null}
     * @param element the object to add at the last index of the new array
     * @return A new array containing the existing elements plus the new element
     * @since 2.1
     */
    public static float[] add(final float[] array, final float element) {
        final float[] newArray = (float[]) copyArrayGrow1(array, Float.TYPE);
        newArray[newArray.length - 1] = element;
        return newArray;
    }

    /**
     * <p>Copies the given array and adds the given element at the end of the new array.
     *
     * <p>The new array contains the same elements of the input
     * array plus the given element in the last position. The component type of
     * the new array is the same as that of the input array.
     *
     * <p>If the input array is {@code null}, a new one element array is returned
     * whose component type is the same as the element.
     *
     * <pre>
     * ArrayUtils.add(null, 0)   = [0]
     * ArrayUtils.add([1], 0)    = [1, 0]
     * ArrayUtils.add([1, 0], 1) = [1, 0, 1]
     * </pre>
     *
     * @param array   the array to copy and add the element to, may be {@code null}
     * @param element the object to add at the last index of the new array
     * @return A new array containing the existing elements plus the new element
     * @since 2.1
     */
    public static int[] add(final int[] array, final int element) {
        final int[] newArray = (int[]) copyArrayGrow1(array, Integer.TYPE);
        newArray[newArray.length - 1] = element;
        return newArray;
    }

    /**
     * <p>Copies the given array and adds the given element at the end of the new array.
     *
     * <p>The new array contains the same elements of the input
     * array plus the given element in the last position. The component type of
     * the new array is the same as that of the input array.
     *
     * <p>If the input array is {@code null}, a new one element array is returned
     * whose component type is the same as the element.
     *
     * <pre>
     * ArrayUtils.add(null, 0)   = [0]
     * ArrayUtils.add([1], 0)    = [1, 0]
     * ArrayUtils.add([1, 0], 1) = [1, 0, 1]
     * </pre>
     *
     * @param array   the array to copy and add the element to, may be {@code null}
     * @param element the object to add at the last index of the new array
     * @return A new array containing the existing elements plus the new element
     * @since 2.1
     */
    public static long[] add(final long[] array, final long element) {
        final long[] newArray = (long[]) copyArrayGrow1(array, Long.TYPE);
        newArray[newArray.length - 1] = element;
        return newArray;
    }

    /**
     * <p>Copies the given array and adds the given element at the end of the new array.
     *
     * <p>The new array contains the same elements of the input
     * array plus the given element in the last position. The component type of
     * the new array is the same as that of the input array.
     *
     * <p>If the input array is {@code null}, a new one element array is returned
     * whose component type is the same as the element.
     *
     * <pre>
     * ArrayUtils.add(null, 0)   = [0]
     * ArrayUtils.add([1], 0)    = [1, 0]
     * ArrayUtils.add([1, 0], 1) = [1, 0, 1]
     * </pre>
     *
     * @param array   the array to copy and add the element to, may be {@code null}
     * @param element the object to add at the last index of the new array
     * @return A new array containing the existing elements plus the new element
     * @since 2.1
     */
    public static short[] add(final short[] array, final short element) {
        final short[] newArray = (short[]) copyArrayGrow1(array, Short.TYPE);
        newArray[newArray.length - 1] = element;
        return newArray;
    }

    @SafeVarargs
    public static <T> T[] array(@Nonnull T... items) {
        return items;
    }

    //-----------------------------------------------------------------------

    /**
     * <p>Shallow clones an array returning a typecast result and handling
     * {@code null}.
     *
     * <p>The objects in the array are not cloned, thus there is no special
     * handling for multi-dimensional arrays.
     *
     * <p>This method returns {@code null} for a {@code null} input array.
     *
     * @param <T>   the component type of the array
     * @param array the array to shallow clone, may be {@code null}
     * @return the cloned array, {@code null} if {@code null} input
     */
    public static <T> T[] clone(final T[] array) {
        if (array == null) {
            return null;
        }
        return array.clone();
    }

    // Reverse
    //-----------------------------------------------------------------------

    /**
     * <p>Clones an array returning a typecast result and handling
     * {@code null}.
     *
     * <p>This method returns {@code null} for a {@code null} input array.
     *
     * @param array the array to clone, may be {@code null}
     * @return the cloned array, {@code null} if {@code null} input
     */
    public static long[] clone(final long[] array) {
        if (array == null) {
            return null;
        }
        return array.clone();
    }

    /**
     * <p>Clones an array returning a typecast result and handling
     * {@code null}.
     *
     * <p>This method returns {@code null} for a {@code null} input array.
     *
     * @param array the array to clone, may be {@code null}
     * @return the cloned array, {@code null} if {@code null} input
     */
    public static int[] clone(final int[] array) {
        if (array == null) {
            return null;
        }
        return array.clone();
    }

    // ----------------------------------------------------------------------

    /**
     * <p>Clones an array returning a typecast result and handling
     * {@code null}.
     *
     * <p>This method returns {@code null} for a {@code null} input array.
     *
     * @param array the array to clone, may be {@code null}
     * @return the cloned array, {@code null} if {@code null} input
     */
    public static short[] clone(final short[] array) {
        if (array == null) {
            return null;
        }
        return array.clone();
    }

    /**
     * <p>Clones an array returning a typecast result and handling
     * {@code null}.
     *
     * <p>This method returns {@code null} for a {@code null} input array.
     *
     * @param array the array to clone, may be {@code null}
     * @return the cloned array, {@code null} if {@code null} input
     */
    public static char[] clone(final char[] array) {
        if (array == null) {
            return null;
        }
        return array.clone();
    }

    /**
     * <p>Clones an array returning a typecast result and handling
     * {@code null}.
     *
     * <p>This method returns {@code null} for a {@code null} input array.
     *
     * @param array the array to clone, may be {@code null}
     * @return the cloned array, {@code null} if {@code null} input
     */
    public static byte[] clone(final byte[] array) {
        if (array == null) {
            return null;
        }
        return array.clone();
    }

    /**
     * <p>Clones an array returning a typecast result and handling
     * {@code null}.
     *
     * <p>This method returns {@code null} for a {@code null} input array.
     *
     * @param array the array to clone, may be {@code null}
     * @return the cloned array, {@code null} if {@code null} input
     */
    public static double[] clone(final double[] array) {
        if (array == null) {
            return null;
        }
        return array.clone();
    }

    /**
     * <p>Clones an array returning a typecast result and handling
     * {@code null}.
     *
     * <p>This method returns {@code null} for a {@code null} input array.
     *
     * @param array the array to clone, may be {@code null}
     * @return the cloned array, {@code null} if {@code null} input
     */
    public static float[] clone(final float[] array) {
        if (array == null) {
            return null;
        }
        return array.clone();
    }

    /**
     * <p>Clones an array returning a typecast result and handling
     * {@code null}.
     *
     * <p>This method returns {@code null} for a {@code null} input array.
     *
     * @param array the array to clone, may be {@code null}
     * @return the cloned array, {@code null} if {@code null} input
     */
    public static boolean[] clone(final boolean[] array) {
        if (array == null) {
            return null;
        }
        return array.clone();
    }

    /**
     * <p>Returns the length of the specified array.
     * This method can deal with {@code Object} arrays and with primitive arrays.
     *
     * <p>If the input array is {@code null}, {@code 0} is returned.
     *
     * <pre>
     * ArrayUtils.getLength(null)            = 0
     * ArrayUtils.getLength([])              = 0
     * ArrayUtils.getLength([null])          = 1
     * ArrayUtils.getLength([true, false])   = 2
     * ArrayUtils.getLength([1, 2, 3])       = 3
     * ArrayUtils.getLength(["a", "b", "c"]) = 3
     * </pre>
     *
     * @param array the array to retrieve the length from, may be null
     * @return The length of the array, or {@code 0} if the array is {@code null}
     * @throws IllegalArgumentException if the object argument is not an array.
     * @since 2.1
     */
    public static int getLength(final Object array) {
        if (array == null) {
            return 0;
        }
        return Array.getLength(array);
    }

    /**
     * <p>Inserts elements into an array at the given index (starting from zero).</p>
     *
     * <p>When an array is returned, it is always a new array.</p>
     *
     * <pre>
     * ArrayUtils.insert(index, null, null)      = null
     * ArrayUtils.insert(index, array, null)     = cloned copy of 'array'
     * ArrayUtils.insert(index, null, values)    = null
     * </pre>
     *
     * @param index  the position within {@code array} to insert the new values
     * @param array  the array to insert the values into, may be {@code null}
     * @param values the new values to insert, may be {@code null}
     * @return The new array.
     * @throws IndexOutOfBoundsException if {@code array} is provided
     *                                   and either {@code index < 0} or {@code index > array.length}
     * @since 3.6
     */
    public static boolean[] insert(final int index, final boolean[] array, final boolean... values) {
        if (array == null) {
            return null;
        }
        if (values == null || values.length == 0) {
            return clone(array);
        }
        if (index < 0 || index > array.length) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Length: " + array.length);
        }

        boolean[] result = new boolean[array.length + values.length];

        System.arraycopy(values, 0, result, index, values.length);
        if (index > 0) {
            System.arraycopy(array, 0, result, 0, index);
        }
        if (index < array.length) {
            System.arraycopy(array, index, result, index + values.length, array.length - index);
        }
        return result;
    }

    /**
     * <p>Inserts elements into an array at the given index (starting from zero).</p>
     *
     * <p>When an array is returned, it is always a new array.</p>
     *
     * <pre>
     * ArrayUtils.insert(index, null, null)      = null
     * ArrayUtils.insert(index, array, null)     = cloned copy of 'array'
     * ArrayUtils.insert(index, null, values)    = null
     * </pre>
     *
     * @param index  the position within {@code array} to insert the new values
     * @param array  the array to insert the values into, may be {@code null}
     * @param values the new values to insert, may be {@code null}
     * @return The new array.
     * @throws IndexOutOfBoundsException if {@code array} is provided
     *                                   and either {@code index < 0} or {@code index > array.length}
     * @since 3.6
     */
    public static byte[] insert(final int index, final byte[] array, final byte... values) {
        if (array == null) {
            return null;
        }
        if (values == null || values.length == 0) {
            return clone(array);
        }
        if (index < 0 || index > array.length) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Length: " + array.length);
        }

        byte[] result = new byte[array.length + values.length];

        System.arraycopy(values, 0, result, index, values.length);
        if (index > 0) {
            System.arraycopy(array, 0, result, 0, index);
        }
        if (index < array.length) {
            System.arraycopy(array, index, result, index + values.length, array.length - index);
        }
        return result;
    }

    // ----------------------------------------------------------------------

    /**
     * <p>Inserts elements into an array at the given index (starting from zero).</p>
     *
     * <p>When an array is returned, it is always a new array.</p>
     *
     * <pre>
     * ArrayUtils.insert(index, null, null)      = null
     * ArrayUtils.insert(index, array, null)     = cloned copy of 'array'
     * ArrayUtils.insert(index, null, values)    = null
     * </pre>
     *
     * @param index  the position within {@code array} to insert the new values
     * @param array  the array to insert the values into, may be {@code null}
     * @param values the new values to insert, may be {@code null}
     * @return The new array.
     * @throws IndexOutOfBoundsException if {@code array} is provided
     *                                   and either {@code index < 0} or {@code index > array.length}
     * @since 3.6
     */
    public static char[] insert(final int index, final char[] array, final char... values) {
        if (array == null) {
            return null;
        }
        if (values == null || values.length == 0) {
            return clone(array);
        }
        if (index < 0 || index > array.length) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Length: " + array.length);
        }

        char[] result = new char[array.length + values.length];

        System.arraycopy(values, 0, result, index, values.length);
        if (index > 0) {
            System.arraycopy(array, 0, result, 0, index);
        }
        if (index < array.length) {
            System.arraycopy(array, index, result, index + values.length, array.length - index);
        }
        return result;
    }

    /**
     * <p>Inserts elements into an array at the given index (starting from zero).</p>
     *
     * <p>When an array is returned, it is always a new array.</p>
     *
     * <pre>
     * ArrayUtils.insert(index, null, null)      = null
     * ArrayUtils.insert(index, array, null)     = cloned copy of 'array'
     * ArrayUtils.insert(index, null, values)    = null
     * </pre>
     *
     * @param index  the position within {@code array} to insert the new values
     * @param array  the array to insert the values into, may be {@code null}
     * @param values the new values to insert, may be {@code null}
     * @return The new array.
     * @throws IndexOutOfBoundsException if {@code array} is provided
     *                                   and either {@code index < 0} or {@code index > array.length}
     * @since 3.6
     */
    public static double[] insert(final int index, final double[] array, final double... values) {
        if (array == null) {
            return null;
        }
        if (values == null || values.length == 0) {
            return clone(array);
        }
        if (index < 0 || index > array.length) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Length: " + array.length);
        }

        double[] result = new double[array.length + values.length];

        System.arraycopy(values, 0, result, index, values.length);
        if (index > 0) {
            System.arraycopy(array, 0, result, 0, index);
        }
        if (index < array.length) {
            System.arraycopy(array, index, result, index + values.length, array.length - index);
        }
        return result;
    }

    /**
     * <p>Inserts elements into an array at the given index (starting from zero).</p>
     *
     * <p>When an array is returned, it is always a new array.</p>
     *
     * <pre>
     * ArrayUtils.insert(index, null, null)      = null
     * ArrayUtils.insert(index, array, null)     = cloned copy of 'array'
     * ArrayUtils.insert(index, null, values)    = null
     * </pre>
     *
     * @param index  the position within {@code array} to insert the new values
     * @param array  the array to insert the values into, may be {@code null}
     * @param values the new values to insert, may be {@code null}
     * @return The new array.
     * @throws IndexOutOfBoundsException if {@code array} is provided
     *                                   and either {@code index < 0} or {@code index > array.length}
     * @since 3.6
     */
    public static float[] insert(final int index, final float[] array, final float... values) {
        if (array == null) {
            return null;
        }
        if (values == null || values.length == 0) {
            return clone(array);
        }
        if (index < 0 || index > array.length) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Length: " + array.length);
        }

        float[] result = new float[array.length + values.length];

        System.arraycopy(values, 0, result, index, values.length);
        if (index > 0) {
            System.arraycopy(array, 0, result, 0, index);
        }
        if (index < array.length) {
            System.arraycopy(array, index, result, index + values.length, array.length - index);
        }
        return result;
    }

    /**
     * <p>Inserts elements into an array at the given index (starting from zero).</p>
     *
     * <p>When an array is returned, it is always a new array.</p>
     *
     * <pre>
     * ArrayUtils.insert(index, null, null)      = null
     * ArrayUtils.insert(index, array, null)     = cloned copy of 'array'
     * ArrayUtils.insert(index, null, values)    = null
     * </pre>
     *
     * @param index  the position within {@code array} to insert the new values
     * @param array  the array to insert the values into, may be {@code null}
     * @param values the new values to insert, may be {@code null}
     * @return The new array.
     * @throws IndexOutOfBoundsException if {@code array} is provided
     *                                   and either {@code index < 0} or {@code index > array.length}
     * @since 3.6
     */
    public static int[] insert(final int index, final int[] array, final int... values) {
        if (array == null) {
            return null;
        }
        if (values == null || values.length == 0) {
            return clone(array);
        }
        if (index < 0 || index > array.length) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Length: " + array.length);
        }

        int[] result = new int[array.length + values.length];

        System.arraycopy(values, 0, result, index, values.length);
        if (index > 0) {
            System.arraycopy(array, 0, result, 0, index);
        }
        if (index < array.length) {
            System.arraycopy(array, index, result, index + values.length, array.length - index);
        }
        return result;
    }

    /**
     * <p>Inserts elements into an array at the given index (starting from zero).</p>
     *
     * <p>When an array is returned, it is always a new array.</p>
     *
     * <pre>
     * ArrayUtils.insert(index, null, null)      = null
     * ArrayUtils.insert(index, array, null)     = cloned copy of 'array'
     * ArrayUtils.insert(index, null, values)    = null
     * </pre>
     *
     * @param index  the position within {@code array} to insert the new values
     * @param array  the array to insert the values into, may be {@code null}
     * @param values the new values to insert, may be {@code null}
     * @return The new array.
     * @throws IndexOutOfBoundsException if {@code array} is provided
     *                                   and either {@code index < 0} or {@code index > array.length}
     * @since 3.6
     */
    public static long[] insert(final int index, final long[] array, final long... values) {
        if (array == null) {
            return null;
        }
        if (values == null || values.length == 0) {
            return clone(array);
        }
        if (index < 0 || index > array.length) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Length: " + array.length);
        }

        long[] result = new long[array.length + values.length];

        System.arraycopy(values, 0, result, index, values.length);
        if (index > 0) {
            System.arraycopy(array, 0, result, 0, index);
        }
        if (index < array.length) {
            System.arraycopy(array, index, result, index + values.length, array.length - index);
        }
        return result;
    }

    /**
     * <p>Inserts elements into an array at the given index (starting from zero).</p>
     *
     * <p>When an array is returned, it is always a new array.</p>
     *
     * <pre>
     * ArrayUtils.insert(index, null, null)      = null
     * ArrayUtils.insert(index, array, null)     = cloned copy of 'array'
     * ArrayUtils.insert(index, null, values)    = null
     * </pre>
     *
     * @param index  the position within {@code array} to insert the new values
     * @param array  the array to insert the values into, may be {@code null}
     * @param values the new values to insert, may be {@code null}
     * @return The new array.
     * @throws IndexOutOfBoundsException if {@code array} is provided
     *                                   and either {@code index < 0} or {@code index > array.length}
     * @since 3.6
     */
    public static short[] insert(final int index, final short[] array, final short... values) {
        if (array == null) {
            return null;
        }
        if (values == null || values.length == 0) {
            return clone(array);
        }
        if (index < 0 || index > array.length) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Length: " + array.length);
        }

        short[] result = new short[array.length + values.length];

        System.arraycopy(values, 0, result, index, values.length);
        if (index > 0) {
            System.arraycopy(array, 0, result, 0, index);
        }
        if (index < array.length) {
            System.arraycopy(array, index, result, index + values.length, array.length - index);
        }
        return result;
    }

    /**
     * <p>Inserts elements into an array at the given index (starting from zero).</p>
     *
     * <p>When an array is returned, it is always a new array.</p>
     *
     * <pre>
     * ArrayUtils.insert(index, null, null)      = null
     * ArrayUtils.insert(index, array, null)     = cloned copy of 'array'
     * ArrayUtils.insert(index, null, values)    = null
     * </pre>
     *
     * @param <T>    The type of elements in {@code array} and {@code values}
     * @param index  the position within {@code array} to insert the new values
     * @param array  the array to insert the values into, may be {@code null}
     * @param values the new values to insert, may be {@code null}
     * @return The new array.
     * @throws IndexOutOfBoundsException if {@code array} is provided
     *                                   and either {@code index < 0} or {@code index > array.length}
     * @since 3.6
     */
    @SafeVarargs
    public static <T> T[] insert(final int index, final T[] array, final T... values) {
        /*
         * Note on use of @SafeVarargs:
         *
         * By returning null when 'array' is null, we avoid returning the vararg
         * array to the caller. We also avoid relying on the type of the vararg
         * array, by inspecting the component type of 'array'.
         */

        if (array == null) {
            return null;
        }
        if (values == null || values.length == 0) {
            return clone(array);
        }
        if (index < 0 || index > array.length) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Length: " + array.length);
        }

        final Class<?> type = array.getClass().getComponentType();
        @SuppressWarnings("unchecked") // OK, because array and values are of type T
                T[] result = (T[]) Array.newInstance(type, array.length + values.length);

        System.arraycopy(values, 0, result, index, values.length);
        if (index > 0) {
            System.arraycopy(array, 0, result, 0, index);
        }
        if (index < array.length) {
            System.arraycopy(array, index, result, index + values.length, array.length - index);
        }
        return result;
    }

    /**
     * <p>Checks if an array of Objects is empty or {@code null}.
     *
     * @param array the array to test
     * @return {@code true} if the array is empty or {@code null}
     * @since 2.1
     */
    public static boolean isEmpty(final Object[] array) {
        return getLength(array) == 0;
    }

    /**
     * <p>Checks if an array of primitive longs is empty or {@code null}.
     *
     * @param array the array to test
     * @return {@code true} if the array is empty or {@code null}
     * @since 2.1
     */
    public static boolean isEmpty(final long[] array) {
        return getLength(array) == 0;
    }

    /**
     * <p>Checks if an array of primitive ints is empty or {@code null}.
     *
     * @param array the array to test
     * @return {@code true} if the array is empty or {@code null}
     * @since 2.1
     */
    public static boolean isEmpty(final int[] array) {
        return getLength(array) == 0;
    }

    /**
     * <p>Checks if an array of primitive shorts is empty or {@code null}.
     *
     * @param array the array to test
     * @return {@code true} if the array is empty or {@code null}
     * @since 2.1
     */
    public static boolean isEmpty(final short[] array) {
        return getLength(array) == 0;
    }

    /**
     * <p>Checks if an array of primitive chars is empty or {@code null}.
     *
     * @param array the array to test
     * @return {@code true} if the array is empty or {@code null}
     * @since 2.1
     */
    public static boolean isEmpty(final char[] array) {
        return getLength(array) == 0;
    }

    /**
     * <p>Checks if an array of primitive bytes is empty or {@code null}.
     *
     * @param array the array to test
     * @return {@code true} if the array is empty or {@code null}
     * @since 2.1
     */
    public static boolean isEmpty(final byte[] array) {
        return getLength(array) == 0;
    }

    /**
     * <p>Checks if an array of primitive doubles is empty or {@code null}.
     *
     * @param array the array to test
     * @return {@code true} if the array is empty or {@code null}
     * @since 2.1
     */
    public static boolean isEmpty(final double[] array) {
        return getLength(array) == 0;
    }

    /**
     * <p>Checks if an array of primitive floats is empty or {@code null}.
     *
     * @param array the array to test
     * @return {@code true} if the array is empty or {@code null}
     * @since 2.1
     */
    public static boolean isEmpty(final float[] array) {
        return getLength(array) == 0;
    }

    /**
     * <p>Checks if an array of primitive booleans is empty or {@code null}.
     *
     * @param array the array to test
     * @return {@code true} if the array is empty or {@code null}
     * @since 2.1
     */
    public static boolean isEmpty(final boolean[] array) {
        return getLength(array) == 0;
    }

    /**
     * <p>Checks if an array of Objects is not empty and not {@code null}.
     *
     * @param <T>   the component type of the array
     * @param array the array to test
     * @return {@code true} if the array is not empty and not {@code null}
     * @since 2.5
     */
    public static <T> boolean isNotEmpty(final T[] array) {
        return !isEmpty(array);
    }

    /**
     * <p>Checks if an array of primitive longs is not empty and not {@code null}.
     *
     * @param array the array to test
     * @return {@code true} if the array is not empty and not {@code null}
     * @since 2.5
     */
    public static boolean isNotEmpty(final long[] array) {
        return !isEmpty(array);
    }

    /**
     * <p>Checks if an array of primitive ints is not empty and not {@code null}.
     *
     * @param array the array to test
     * @return {@code true} if the array is not empty and not {@code null}
     * @since 2.5
     */
    public static boolean isNotEmpty(final int[] array) {
        return !isEmpty(array);
    }

    /**
     * <p>Checks if an array of primitive shorts is not empty and not {@code null}.
     *
     * @param array the array to test
     * @return {@code true} if the array is not empty and not {@code null}
     * @since 2.5
     */
    public static boolean isNotEmpty(final short[] array) {
        return !isEmpty(array);
    }

    /**
     * <p>Checks if an array of primitive chars is not empty and not {@code null}.
     *
     * @param array the array to test
     * @return {@code true} if the array is not empty and not {@code null}
     * @since 2.5
     */
    public static boolean isNotEmpty(final char[] array) {
        return !isEmpty(array);
    }

    /**
     * <p>Checks if an array of primitive bytes is not empty and not {@code null}.
     *
     * @param array the array to test
     * @return {@code true} if the array is not empty and not {@code null}
     * @since 2.5
     */
    public static boolean isNotEmpty(final byte[] array) {
        return !isEmpty(array);
    }

    /**
     * <p>Checks if an array of primitive doubles is not empty and not {@code null}.
     *
     * @param array the array to test
     * @return {@code true} if the array is not empty and not {@code null}
     * @since 2.5
     */
    public static boolean isNotEmpty(final double[] array) {
        return !isEmpty(array);
    }

    /**
     * <p>Checks if an array of primitive floats is not empty and not {@code null}.
     *
     * @param array the array to test
     * @return {@code true} if the array is not empty and not {@code null}
     * @since 2.5
     */
    public static boolean isNotEmpty(final float[] array) {
        return !isEmpty(array);
    }

    /**
     * <p>Checks if an array of primitive booleans is not empty and not {@code null}.
     *
     * @param array the array to test
     * @return {@code true} if the array is not empty and not {@code null}
     * @since 2.5
     */
    public static boolean isNotEmpty(final boolean[] array) {
        return !isEmpty(array);
    }

    /**
     * <p>Reverses the order of the given array.
     *
     * <p>There is no special handling for multi-dimensional arrays.
     *
     * <p>This method does nothing for a {@code null} input array.
     *
     * @param array the array to reverse, may be {@code null}
     */
    public static void reverse(final Object[] array) {
        if (array == null) {
            return;
        }
        reverse(array, 0, array.length);
    }

    /**
     * <p>
     * Reverses the order of the given array in the given range.
     *
     * <p>
     * This method does nothing for a {@code null} input array.
     *
     * @param array               the array to reverse, may be {@code null}
     * @param startIndexInclusive the starting index. Under value (&lt;0) is promoted to 0, over value
     *                            (&gt;array.length) results in no
     *                            change.
     * @param endIndexExclusive   elements up to endIndex-1 are reversed in the array. Under value (&lt; start index)
     *                            results in no
     *                            change. Over value (&gt;array.length) is demoted to array length.
     * @since 3.2
     */
    public static void reverse(final Object[] array, final int startIndexInclusive, final int endIndexExclusive) {
        if (array == null) {
            return;
        }
        int i = startIndexInclusive < 0 ? 0 : startIndexInclusive;
        int j = Math.min(array.length, endIndexExclusive) - 1;
        Object tmp;
        while (j > i) {
            tmp = array[j];
            array[j] = array[i];
            array[i] = tmp;
            j--;
            i++;
        }
    }

    /**
     * <p>ArrayUtils instances should NOT be constructed in standard programming.
     * Instead, the class should be used as <code>ArrayUtils.clone(new int[] {2})</code>.
     *
     * <p>This constructor is public to permit tools that require a JavaBean instance
     * to operate.
     */
    public ArrayUtils() {
        super();
    }

    /**
     * Returns a copy of the given array of size 1 greater than the argument.
     * The last value of the array is left to the default value.
     *
     * @param array                 The array to copy, must not be {@code null}.
     * @param newArrayComponentType If {@code array} is {@code null}, create a
     *                              size 1 array of this type.
     * @return A new copy of the array of size 1 greater than the input.
     */
    private static Object copyArrayGrow1(final Object array, final Class<?> newArrayComponentType) {
        if (array != null) {
            final int arrayLength = Array.getLength(array);
            final Object newArray = Array.newInstance(array.getClass().getComponentType(), arrayLength + 1);
            System.arraycopy(array, 0, newArray, 0, arrayLength);
            return newArray;
        }
        return Array.newInstance(newArrayComponentType, 1);
    }
}