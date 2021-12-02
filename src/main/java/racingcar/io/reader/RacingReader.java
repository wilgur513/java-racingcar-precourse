package racingcar.io.reader;

import java.util.List;
import racingcar.model.RacingInfo;

public class RacingReader {
	private final Reader<List<String>> carNameReader;
	private final Reader<Integer> turnValueReader;

	public RacingReader(Reader<List<String>> carNameReader, Reader<Integer> turnValueReader) {
		this.carNameReader = carNameReader;
		this.turnValueReader = turnValueReader;
	}

	public RacingInfo read() {
		List<String> carNames = readCarNames();
		Integer turnValue = readTurnValue();
		return RacingInfo.of(turnValue, carNames);
	}

	private List<String> readCarNames() {
		try {
			return carNameReader.read();
		} catch (IllegalArgumentException e) {
			return readCarNames();
		}
	}

	private Integer readTurnValue() {
		try {
			return turnValueReader.read();
		} catch (IllegalArgumentException e) {
			return readTurnValue();
		}
	}
}