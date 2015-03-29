package net.stevemoyer.vbrank;

import java.util.Arrays;
import java.util.List;

import com.google.inject.Module;
import com.squarespace.jersey2.guice.JerseyGuiceServletContextListener;


public class VbRankApplication extends JerseyGuiceServletContextListener {

  @Override
  protected List<? extends Module> modules() {
    return Arrays.asList(new VbRankModule());
  }
}
