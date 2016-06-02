package me.xiaopan.assemblyadaptersample.itemfactory;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import me.xiaopan.assemblyadapter.OnGroupLoadMoreListener;
import me.xiaopan.assemblyadaptersample.R;
import me.xiaopan.assemblyadapter.AbstractLoadMoreGroupItemFactory;

public class LoadMoreGroupItemFactory extends AbstractLoadMoreGroupItemFactory {

    public LoadMoreGroupItemFactory(OnGroupLoadMoreListener eventListener) {
        super(eventListener);
    }

    @Override
    public AbstractLoadMoreGroupItem createAssemblyItem(ViewGroup parent) {
        return new LoadMoreGroupItem(parent, this);
    }

    public static class LoadMoreGroupItem extends AbstractLoadMoreGroupItem {
        private View loadingView;
        private View errorView;
        private View endView;

        protected LoadMoreGroupItem(ViewGroup parent, AbstractLoadMoreGroupItemFactory baseFactory) {
            super(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_load_more, parent, false), baseFactory);
        }

        @Override
        protected void onFindViews(View convertView) {
            loadingView = convertView.findViewById(R.id.text_loadMoreListItem_loading);
            errorView = convertView.findViewById(R.id.text_loadMoreListItem_error);
            endView = convertView.findViewById(R.id.text_loadMoreListItem_end);
        }

        @Override
        public View getErrorRetryView() {
            return errorView;
        }

        @Override
        public void showLoading() {
            loadingView.setVisibility(View.VISIBLE);
            errorView.setVisibility(View.INVISIBLE);
            endView.setVisibility(View.INVISIBLE);
        }

        @Override
        public void showErrorRetry() {
            loadingView.setVisibility(View.INVISIBLE);
            errorView.setVisibility(View.VISIBLE);
            endView.setVisibility(View.INVISIBLE);
        }

        @Override
        public void showEnd() {
            loadingView.setVisibility(View.INVISIBLE);
            errorView.setVisibility(View.INVISIBLE);
            endView.setVisibility(View.VISIBLE);
        }
    }
}
