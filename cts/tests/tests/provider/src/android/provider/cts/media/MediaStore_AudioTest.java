/*
 * Copyright (C) 2009 The Android Open Source Project
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

package android.provider.cts.media;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import android.provider.MediaStore.Audio;

import androidx.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class MediaStore_AudioTest {
    private String mKeyForBeatles;

    @Before
    public void setUp() throws Exception {
        mKeyForBeatles = Audio.keyFor("beatles");
    }

    @Test
    public void testKeyFor() {
        assertEquals(mKeyForBeatles, Audio.keyFor("[beatles]"));
        assertEquals(mKeyForBeatles, Audio.keyFor("(beatles)"));
        assertEquals(mKeyForBeatles, Audio.keyFor("beatles!"));
        assertEquals(mKeyForBeatles, Audio.keyFor("beatles?"));
        assertEquals(mKeyForBeatles, Audio.keyFor("'beatles'"));
        assertEquals(mKeyForBeatles, Audio.keyFor("beatles."));
        assertEquals(mKeyForBeatles, Audio.keyFor("beatles,"));

        assertEquals(mKeyForBeatles, Audio.keyFor("  beatles  "));

        assertEquals(mKeyForBeatles, Audio.keyFor("BEATLES"));

        assertEquals(mKeyForBeatles, Audio.keyFor("the beatles"));
        assertEquals(mKeyForBeatles, Audio.keyFor("a beatles"));
        assertEquals(mKeyForBeatles, Audio.keyFor("an beatles"));

        assertEquals(mKeyForBeatles, Audio.keyFor("beatles,the"));
        assertEquals(mKeyForBeatles, Audio.keyFor("beatles,a"));
        assertEquals(mKeyForBeatles, Audio.keyFor("beatles,an"));

        assertEquals(mKeyForBeatles, Audio.keyFor("beatles, the"));
        assertEquals(mKeyForBeatles, Audio.keyFor("beatles, a"));
        assertEquals(mKeyForBeatles, Audio.keyFor("beatles, an"));

        // test sorting
        assertTrue(Audio.keyFor("areosmith").compareTo(mKeyForBeatles) < 0);
        assertTrue(Audio.keyFor("coldplay").compareTo(mKeyForBeatles) > 0);

        // test accented characters
        assertTrue(Audio.keyFor("¿Aómo esto funciona?").compareTo(mKeyForBeatles) < 0);
        assertTrue(Audio.keyFor("¿Cómo esto funciona?").compareTo(mKeyForBeatles) > 0);
        assertTrue(Audio.keyFor("Le passé composé").compareTo(mKeyForBeatles) > 0);
    }
}
