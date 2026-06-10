package njt.njt_projekat.mapper.impl;

import org.springframework.stereotype.Component;

import njt.njt_projekat.dto.impl.DostavljacDto;
import njt.njt_projekat.entity.impl.Dostavljac;
import njt.njt_projekat.mapper.DtoEntityMapper;

@Component
public class DostavljacMapper implements DtoEntityMapper<DostavljacDto, Dostavljac> {

	@Override
	public DostavljacDto toDto(Dostavljac e) {
		if (e == null) {
			return null;
		}
		return new DostavljacDto(e.getDostavljacId(), e.getIme(), e.getPrezime(), e.getTelefon());
	}

	@Override
	public Dostavljac toEntity(DostavljacDto t) {
		if (t == null) {
			return null;
		}
		return new Dostavljac(t.getDostavljacId(), t.getIme(), t.getPrezime(), t.getTelefon());
	}

}
