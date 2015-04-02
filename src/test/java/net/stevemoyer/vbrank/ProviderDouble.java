package net.stevemoyer.vbrank;
import static org.mockito.Mockito.*;
import com.google.inject.Provider;

public class ProviderDouble {

  public static<T> Provider<T> provide(T val) {
    @SuppressWarnings("unchecked")
    Provider<T> ret = (Provider<T>) mock(Provider.class);
    when(ret.get()).thenReturn(val);
    return ret;
  }
}
