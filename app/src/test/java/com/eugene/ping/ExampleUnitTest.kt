package com.eugene.ping.components

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Mockito.verify

class GameTest {
  @Mock
  lateinit var game: Game

  @Before
  fun setup() {
    MockitoAnnotations.initMocks(this)
  }

  @Test
  fun testRender() {
    game.render()

    verify(game, times(1)).render())
  }
  
  @Test
  fun testProcessInput() {
    game.processInput()

    verify(game, times(1)).processInput())
  }
  
  @Test
  fun testUpdate() {
    game.update()

    verify(game, times(1)).update())
  }
  
  @After
  public void teardown() throws Exception {
	 game = null
  }

}