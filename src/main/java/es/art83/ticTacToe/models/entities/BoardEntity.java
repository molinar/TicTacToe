package es.art83.ticTacToe.models.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import es.art83.ticTacToe.models.utils.ColorModel;
import es.art83.ticTacToe.models.utils.DirectionModel;

@Entity
public class BoardEntity implements Cloneable {

    @Id
    @GeneratedValue
    private int id;

    // TODO debería ser un set porque no hay orden de secuencia por ningún
    // criterio
    @OneToMany(cascade = CascadeType.ALL)
    private List<PieceEntity> pieces;

    public BoardEntity() {
        this(new ArrayList<>());
    }

    public BoardEntity(List<PieceEntity> list) {
        this.setPieces(list);
    }

    public List<PieceEntity> getPieces() {
        return this.pieces;
    }

    private void setPieces(List<PieceEntity> pieces) {
        assert pieces != null;
        this.pieces = pieces;
    }

    public List<CoordinateEntity> coordinates(ColorModel color) {
        assert color != null;
        List<CoordinateEntity> coordinates = new ArrayList<>();
        for (PieceEntity ficha : pieces) {
            if (ficha.getColor() == color)
                coordinates.add(ficha.getCoordinate());
        }
        return coordinates;
    }

    public List<CoordinateEntity> validDestinationCoordinates() {
        List<CoordinateEntity> coordinates = CoordinateEntity.allCoordinates();
        for (PieceEntity ficha : pieces) {
            coordinates.remove(ficha.getCoordinate());
        }
        return coordinates;
    }

    public boolean hasAllPieces() {
        return this.pieces.size() == CoordinateEntity.DIMENSION * 2;
    }

    // TODO se debería quitar, no se usa y es redundante
    public boolean existTicTacToe() {
        for (ColorModel color : ColorModel.values()) {
            if (existTicTacToe(color))
                return true;
        }
        return false;
    }

    public boolean existTicTacToe(ColorModel color) {
        assert color != null;
        List<CoordinateEntity> coordinates = this.coordinates(color);
        if (coordinates.size() < CoordinateEntity.DIMENSION) {
            return false;
        } else {
            CoordinateEntity firstCoordinate = coordinates.get(0);
            coordinates.remove(firstCoordinate);
            return DirectionModel.WITHOUT_DIRECTION != firstCoordinate.inDirection(coordinates);
        }
    }

    // LUIS debería irse al bean que lo necesite y él pide getPieces
    public ColorModel[][] colors() {
        ColorModel[][] matriz = new ColorModel[3][3];
        for (PieceEntity ficha : pieces) {
            matriz[ficha.getCoordinate().getRow()][ficha.getCoordinate().getColumn()] = ficha
                    .getColor();
        }
        return matriz;
    }

    public void put(PieceEntity ficha) {
        this.pieces.add(ficha);
    }

    public void remove(CoordinateEntity coordinate) {
        assert coordinate != null : "parametro null";
        System.out.println("----");
        System.out.println(coordinate);
        System.out.println(pieces);
        for (PieceEntity piece : pieces) {
            if (piece.getCoordinate().equals(coordinate)) {
                this.pieces.remove(piece);
                System.out.println("salgo");
                return;
            }
        }
        assert false : "Se intentó borrar un elemento que no existe";
    }

    public void clear() {
        this.pieces.clear();
    }

    public void update(BoardEntity board) {
        List<PieceEntity> pieces = new ArrayList<>(board.pieces);
        this.setPieces(pieces);
    }

    @Override
    public String toString() {
        return "BoardEntity[" + pieces + "]";
    }

    @Override
    public BoardEntity clone() {
        List<PieceEntity> pieces = new ArrayList<>();
        for (PieceEntity pieceEntity : this.pieces) {
            pieces.add(pieceEntity.clone());
        }
        return new BoardEntity(pieces);
    }

}
