import java.nio.channels.SocketChannel;

public class SocketModel
{
    private SocketChannel	_socketChannel;
    private String          _remoteAddress;
    private int				_id;
    private static int		_idCount;

    public SocketModel(SocketChannel socketChannel, String remoteAddress)
    {
        this._id = this.getNextId();
        this._socketChannel = socketChannel;
        this._remoteAddress = remoteAddress;
    }

    private int getNextId()
    {
        return (++this._idCount);
    }

    public int getId() { return (this._id); }

    public String getRemoteAddress() { return (this._remoteAddress); }

    public SocketChannel getSocketChannel() { return (this._socketChannel); }
}