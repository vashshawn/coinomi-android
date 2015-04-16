package com.reddcoin.wallet.ui.widget;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Checkable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.reddcoin.core.coins.CoinType;
import com.reddcoin.core.coins.Value;
import com.reddcoin.core.util.GenericUtils;
import com.reddcoin.wallet.Constants;
import com.reddcoin.wallet.ExchangeRatesProvider.ExchangeRate;
import com.reddcoin.wallet.R;

/**
 * @author John L. Jegutanis
 */
public class CoinListItem extends LinearLayout implements Checkable {
    private final TextView title;
    private final ImageView icon;
    private final View view;
    private final Amount rateView;

    private boolean isChecked = false;
    private CoinType type;

    public CoinListItem(Context context) {
        super(context);

        view = LayoutInflater.from(context).inflate(R.layout.coin_list_row, this, true);
        title = (TextView) findViewById(R.id.item_text);
        icon = (ImageView) findViewById(R.id.item_icon);
        rateView = (Amount) findViewById(R.id.exchange_rate);
    }

    public void setCoin(CoinType type) {
        this.type = type;
        title.setText(type.getName());
        icon.setImageResource(Constants.COIN_ICON);
    }

    public void setExchangeRate(ExchangeRate exchangeRate) {
        if (exchangeRate != null && type != null) {
            Value localAmount = exchangeRate.rate.convert(type.oneCoin());
            rateView.setAmount(GenericUtils.formatFiatValue(localAmount));
            rateView.setSymbol(localAmount.type.getSymbol());
            rateView.setVisibility(View.VISIBLE);
        } else {
            rateView.setVisibility(View.GONE);
        }
    }

    @Override
    public void setChecked(boolean checked) {
        isChecked = checked;

        if (isChecked) {
            view.setBackgroundResource(R.color.primary_100);
        } else {
            view.setBackgroundResource(0);
        }
    }

    @Override
    public boolean isChecked() {
        return isChecked;
    }

    @Override
    public void toggle() {
        setChecked(!isChecked);
    }
}
